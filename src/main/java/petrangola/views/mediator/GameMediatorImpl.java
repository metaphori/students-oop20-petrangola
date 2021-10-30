package petrangola.views.mediator;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import petrangola.controllers.game.GameController;
import petrangola.controllers.player.DealerController;
import petrangola.models.cards.Cards;
import petrangola.models.game.Game;
import petrangola.models.player.Player;
import petrangola.models.player.PlayerDetail;
import petrangola.views.ViewFX;
import petrangola.views.animation.player.DealerAnimation;
import petrangola.views.animation.player.DealerAnimationImpl;
import petrangola.views.components.layout.LayoutBuilder;
import petrangola.views.events.NextRoundEvent;
import petrangola.views.events.NextTurnEvent;
import petrangola.views.events.WinnerEvent;
import petrangola.views.game.*;
import petrangola.views.player.*;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class GameMediatorImpl implements GameMediator {
  private static final String EXCHANGE = ".Exchange";
  private static final String KNOCK = ".Knock";
  
  private final CurrentPlayer currentPlayer = new CurrentPlayerImpl();
  private final UsernameView usernameView = new UsernameViewImpl(new Text());
  private final RoundView roundView = new RoundViewImpl(new Text());
  private final WinnerView winnerView = new WinnerViewImpl(new Text());
  private final KnockView knockView = new KnockViewImpl(new Text());
  
  private final LayoutBuilder layoutBuilder;
  private final GameController gameController;
  private final DealerController dealerController;
  private final DealerAnimation dealerAnimation;
  private CardsMediator cardsMediator;
  private HighCardMediator highCardMediator;
  
  public GameMediatorImpl(LayoutBuilder layoutBuilder, GameController gameController, DealerController dealerController) {
    this.layoutBuilder = layoutBuilder;
    this.gameController = gameController;
    this.dealerController = dealerController;
    this.dealerAnimation = new DealerAnimationImpl(this.layoutBuilder.getLayout());
  }
  
  @Override
  public void onDealer(Game game) {
    this.getGameController().setTurnNumbers();
  
    this.getCurrentPlayer().setPlayer(game.getDealer());
    
    this.getDealerController().setDealer(game.getDealer());
    
    List<PlayerDetail> playersDetails = game.getPlayersDetails()
                                              .stream()
                                              .filter(PlayerDetail::isStillAlive)
                                              .collect(Collectors.toList());
    
    this.getDealerAnimation().setPlayersDetails(playersDetails);
    
    this.getDealerAnimation().cleanUp();
    this.getDealerAnimation().setDealerController(getDealerController());
    this.getDealerAnimation()
          .addKeyFrame(Duration.millis(600), getDealerAnimation().showDealerName())
          .addKeyFrame(Duration.millis(2600), getDealerAnimation().hideHighCards(getHighCardMediator()))
          .addKeyFrame(Duration.millis(3000), getDealerAnimation().dealCards())
          .play();
  }
  
  @Override
  public void onRound(Game game) {
    int round = game.getRound();
    
    if (round == 1) {
      getCardsMediator().hideDealerView(getLayout());
      getCardsMediator().showBoardCards();
      getCardsMediator().showUserCards();
    }
    
    this.updateRoundTextView(String.valueOf(round));
    
    if (game.getLastKnocker() != null && !game.getLastKnocker().isEmpty() && round > 1) {
      this.launchWinnerEvent(game);
    }
  }
  
  @Override
  public void onCurrentTurnNumber(Game game, Player player) {
    if (game.isOnlyOneRound() && this.gameController.isLastKnockerPlayerTurn()) {
      this.launchWinnerEvent(game);
      return;
    }
    
    if (this.gameController.isLastPlayerTurn()) {
      EventBus.getDefault().post(new NextRoundEvent());
    }
    
    this.getCurrentPlayer().setPlayer(player);
    this.getCardsMediator().setCurrentPlayerCards(this.getCurrentPlayer());
    this.updateUsernameView(this.getCurrentPlayer().getPlayer().getUsername());
    
    GameObjectView.toggleVisibilityUserButton(this.getLayout(), List.of(EXCHANGE, KNOCK), player.isNPC());
    
    if (player.isNPC()) {
      this.getCardsMediator().npcExchangeCards(player);
    }
  }
  
  @Override
  public void onKnockerCount(Game game) {
    if (this.gameController.checkKnocks()) {
      this.launchWinnerEvent(game);
      return;
    }
    
    updateKnockView(String.valueOf(game.getKnockerCount()));
    
    EventBus.getDefault().post(new NextTurnEvent());
  }
  
  @Override
  public void onWinner(String winnerName) {
    this.cardsMediator.unregister(getLayout());
    this.cardsMediator.showNPCCards(); // I'm not gonna delete this, the animation that is responsible for that would take too much time to be implemented now
    this.updateWinnerView(winnerName);
  }
  
  
  @Override
  public void register(Pane layout) {
    final Pane userPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    ViewFX.addOrUpdate(userPane, getUsernameView().get());
    
    final Pane roundPane = (Pane) layout.lookup(GameStyleClass.ROUND.getAsStyleClass());
    ViewFX.addOrUpdate(roundPane, getRoundView().get());
    
    final Pane knockPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    ViewFX.addOrUpdate(knockPane, getKnockView().get());
    
    final Pane winnerPane = (Pane) layout.lookup(GameStyleClass.WINNER.getAsStyleClass());
    ViewFX.addOrUpdate(winnerPane, getWinnerView().get());
  }
  
  @Override
  public void unregister(Pane layout) {
    final Pane userPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    userPane.getChildren().clear();
    
    final Pane roundPane = (Pane) layout.lookup(GameStyleClass.ROUND.getAsStyleClass());
    roundPane.getChildren().clear();
    
    final Pane knockPane = (Pane) layout.lookup(GameStyleClass.KNOCKS.getAsStyleClass());
    knockPane.getChildren().clear();
  
    final Pane lifePane = (Pane) layout.lookup(GameStyleClass.LIFE.getAsStyleClass());
    lifePane.getChildren().clear();
  }
  
  @Override
  public void update(String propertyName, Object source) {
    final Game game = (Game) source;
    
    switch (propertyName) {
      case "dealtCards":
        List<Cards> cardsList = game.getCards();
        
        this.getCurrentPlayer().setPlayer(game.getDealer());
        
        if (game.getDealer().isNPC()) {
          this.getDealerController().cherryPickingCombination(this.getBoardCards(cardsList), this.getCurrentPlayerCards(cardsList));
        }
        
        break;
      case "cards":
        this.getCardsMediator().setLayoutBuilder(this.layoutBuilder);
        
        if (!game.getCards().isEmpty()) {
          this.getCardsMediator().register(getLayout());
          this.getCardsMediator().showDealerView(getLayout());
        }
        
        break;
      case "playersDetails":
        List<PlayerDetail> playersDetails = game.getPlayersDetails()
                                                  .stream()
                                                  .filter(PlayerDetail::isStillAlive)
                                                  .collect(Collectors.toList());
        
        this.getDealerAnimation().setPlayersDetails(playersDetails);
        break;
      case "board":
        this.onBoard(game);
        break;
      case "dealer":
        this.onDealer(game);
        break;
      case "round":
        this.onRound(game);
        break;
      case "knockerCount":
        if (isWinnerNotSet(game)) {
          this.onKnockerCount(game);
        }
        
        break;
      case "currentTurnNumber":
        if (game.getCards().isEmpty()) {
          break;
        }
        
        game.getPlayersDetails()
              .stream()
              .filter(PlayerDetail::isStillAlive)
              .filter(playerDetail -> playerDetail.getTurnNumber() == game.getCurrentTurnNumber())
              .findFirst()
              .ifPresent(playerDetail -> this.onCurrentTurnNumber(game, playerDetail.getPlayer()));
        break;
      case "winner":
        if (!game.getWinner().isEmpty()) {
          this.onWinner(game.getWinner());
        }
        
        break;
    }
  }
  
  @Override
  public CardsMediator getCardsMediator() {
    return this.cardsMediator;
  }
  
  @Override
  public void setCardsMediator(CardsMediator cardsMediator) {
    this.cardsMediator = cardsMediator;
  }
  
  @Override
  public void onBoard(Game game) {
    this.getDealerAnimation().setBoard(game.getBoard());
    this.getHighCardMediator().setPlayersDetails(game.getPlayersDetails().stream().filter(PlayerDetail::isStillAlive).collect(Collectors.toList()));
    this.getHighCardMediator().register(this.getLayout());
  }
  
  private HighCardMediator getHighCardMediator() {
    return this.highCardMediator;
  }
  
  @Override
  public void setHighCardMediator(HighCardMediator highCardMediator) {
    this.highCardMediator = highCardMediator;
  }
  
  private void updateUsernameView(String text) {
    this.usernameView.updateOrCreateTextViewFX(getLayout(), GameStyleClass.USERNAME.getAsStyleClass(), text);
  }
  
  private void updateRoundTextView(String text) {
    this.roundView.updateOrCreateTextViewFX(getLayout(), GameStyleClass.ROUND.getAsStyleClass(), text);
  }
  
  private void updateKnockView(String text) {
    this.knockView.updateOrCreateTextViewFX(getLayout(), GameStyleClass.ROUND.getAsStyleClass(), text);
  }
  
  private void updateWinnerView(String text) {
    this.winnerView.updateOrCreateTextViewFX(getLayout(), GameStyleClass.WINNER.getAsStyleClass(), text);
  }
  
  private GameController getGameController() {
    return this.gameController;
  }
  
  private Pane getLayout() {
    return this.layoutBuilder.getLayout();
  }
  
  private CurrentPlayer getCurrentPlayer() {
    return this.currentPlayer;
  }
  
  private DealerAnimation getDealerAnimation() {
    return this.dealerAnimation;
  }
  
  private void launchWinnerEvent(Game game) {
    EventBus.getDefault().post(new WinnerEvent(game, this));
  }
  
  private UsernameView getUsernameView() {
    return this.usernameView;
  }
  
  private RoundView getRoundView() {
    return this.roundView;
  }
  
  private WinnerView getWinnerView() {
    return this.winnerView;
  }
  
  private KnockView getKnockView() {
    return this.knockView;
  }
  
  private DealerController getDealerController() {
    return dealerController;
  }
  
  private Cards getCurrentPlayerCards(List<Cards> cardsList) {
    return cardsList
                 .stream()
                 .filter(Cards::isPlayerCards)
                 .filter(cards -> cards.getPlayer()
                                        .map(Player::getUsername)
                                        .stream()
                                        .anyMatch(username -> username.equals(getCurrentPlayer().getPlayer().getUsername())))
                 .findFirst()
                 .orElse(null);
  }
  
  private Cards getBoardCards(List<Cards> cardsList) {
    return cardsList
                 .stream()
                 .filter(Cards::isCommunity)
                 .findFirst()
                 .orElse(null);
  }
  
  private boolean isWinnerNotSet(Game game) {
    return game.getWinner() == null || game.getWinner().isEmpty();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GameMediatorImpl)) return false;
    GameMediatorImpl that = (GameMediatorImpl) o;
    return getCurrentPlayer().equals(that.getCurrentPlayer()) && getUsernameView().equals(that.getUsernameView()) && getRoundView().equals(that.getRoundView()) && getWinnerView().equals(that.getWinnerView()) && getKnockView().equals(that.getKnockView()) && layoutBuilder.equals(that.layoutBuilder) && getGameController().equals(that.getGameController()) && getDealerController().equals(that.getDealerController()) && getDealerAnimation().equals(that.getDealerAnimation()) && Objects.equals(getCardsMediator(), that.getCardsMediator()) && Objects.equals(getHighCardMediator(), that.getHighCardMediator());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getCurrentPlayer(), getUsernameView(), getRoundView(), getWinnerView(), getKnockView(), layoutBuilder, getGameController(), getDealerController(), getDealerAnimation(), getCardsMediator(), getHighCardMediator());
  }
}
