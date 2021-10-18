package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.events.NextTurnEvent;
import main.java.petrangola.views.events.WinnerEvent;
import main.java.petrangola.views.game.*;
import main.java.petrangola.views.player.*;
import main.java.petrangola.views.player.animation.DealerAnimation;
import main.java.petrangola.views.player.animation.DealerAnimationImpl;
import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class GameMediatorImpl implements GameMediator {
  private final CurrentPlayer currentPlayer = new CurrentPlayerImpl();
  
  private final LifeView lifeView = new LifeViewImpl(new Text());
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
  public void onDealer(Dealer dealer) {
    getGameController().setTurnNumbers();
    
    getCurrentPlayer().setPlayer(dealer);
    getDealerController().setDealer(dealer);
    
    getDealerAnimation().setDealerController(getDealerController());
    getDealerAnimation()
          .addKeyFrame(Duration.millis(600), getDealerAnimation().showDealerName())
          .addKeyFrame(Duration.millis(2600), getDealerAnimation().hideHighCards(getHighCardMediator()))
          .addKeyFrame(Duration.millis(3000), getDealerAnimation().dealCards())
          .play();
  }
  
  @Override
  public void onRound(int round) {
    if (round == 1) {
      getCardsMediator().hideDealerView(getLayout());
      getCardsMediator().showBoardCards();
      getCardsMediator().showUserCards();
    }
    
    this.updateRoundTextView(String.valueOf(round));
  }
  
  @Override
  public void onCurrentTurnNumber(Player player) {
    this.getCurrentPlayer().setPlayer(player);
    this.getCardsMediator().setCurrentPlayerCards(getCurrentPlayer());
    this.getCardsMediator().toggleUserButton(getCurrentPlayer());
    this.updateUsernameView(getCurrentPlayer().getPlayer().getUsername());
    
    if (player.isNPC()) {
      this.getCardsMediator().npcExchangeCards(player);
    }
  }
  
  @Override
  public void onKnockerCount(Game game) {
    if (this.gameController.checkKnocks()) {
      this.launchWinnerEvent(game);
    } else {
      EventBus.getDefault().post(new NextTurnEvent());
    }
  }
  
  @Override
  public void onLastKnocker() {
    // empty
  }
  
  @Override
  public void onWinner(String winnerName) {
    this.cardsMediator.showNPCCards();
    this.updateWinnerView(winnerName);
    this.unregister(getLayout());
    this.cardsMediator.unregister(getLayout());
  }
  
  @Override
  public void onOnlyOneRound(Game game) {
    if (this.gameController.isLastPlayerTurn()) {
      this.launchWinnerEvent(game);
    }
  }
  
  
  @Override
  public void register(Pane layout) {
    final Pane userPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    userPane.getChildren().add(getUsernameView().get());
    
    final Pane lifeView = (Pane) layout.lookup(GameStyleClass.LIFE.getAsStyleClass());
    lifeView.getChildren().add(getLifeView().get());
    
    final Pane roundPane = (Pane) layout.lookup(GameStyleClass.ROUND.getAsStyleClass());
    roundPane.getChildren().add(getRoundView().get());
    
    final Pane knockPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    knockPane.getChildren().add(getKnockView().get());
    
    final Pane winnerPane = (Pane) layout.lookup(GameStyleClass.WINNER.getAsStyleClass());
    winnerPane.getChildren().add(getWinnerView().get());
  }
  
  @Override
  public void unregister(Pane layout) {
    final Pane userPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    userPane.getChildren().clear();
    
    final Pane lifeView = (Pane) layout.lookup(GameStyleClass.LIFE.getAsStyleClass());
    lifeView.getChildren().clear();
    
    final Pane roundPane = (Pane) layout.lookup(GameStyleClass.ROUND.getAsStyleClass());
    roundPane.getChildren().clear();
    
    final Pane knockPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
    knockPane.getChildren().clear();
    
    final Pane winnerPane = (Pane) layout.lookup(GameStyleClass.WINNER.getAsStyleClass());
    winnerPane.getChildren().clear();
  }
  
  @Override
  public void update(String propertyName, Object source) {
    final Game game = (Game) source;
    
    switch (propertyName) {
      case "dealtCards":
        List<Cards> cardsList = game.getCards();
        
        if (getCurrentPlayer().getPlayer().isNPC()) {
          this.getDealerController().cherryPickingCombination(this.getBoardCards(cardsList), this.getCurrentPlayerCards(cardsList));
        }
        
        break;
      case "cards":
        this.getCardsMediator().setLayoutBuilder(this.layoutBuilder);
        this.getCardsMediator().register(getLayout());
        this.getCardsMediator().showDealerView(getLayout());
      case "playerDetails":
        // No inspection uncheck for the sole reason that I know for sure the source is a List<PlayerDetails>
        //noinspection unchecked
        this.getDealerAnimation().setPlayerDetails(game.getPlayerDetails());
        break;
      case "board":
        this.onBoard(game);
        break;
      case "dealer":
        this.onDealer(game.getDealer());
        break;
      case "round":
        this.onRound(game.getRound());
        break;
      case "knockerCount":
        this.onKnockerCount(game);
        break;
      case "onlyOneRound":
        this.onOnlyOneRound(game);
        break;
      case "currentTurnNumber":
        game.getPlayerDetails()
              .stream()
              .filter(playerDetail -> playerDetail.getTurnNumber() == game.getCurrentTurnNumber())
              .findFirst()
              .ifPresent(playerDetail -> this.onCurrentTurnNumber(playerDetail.getPlayer()));
        break;
      case "winner":
        this.onWinner(game.getWinner());
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
    this.getHighCardMediator().setPlayersDetails(game.getPlayerDetails());
    this.getHighCardMediator().register(this.getLayout());
  }
  
  private HighCardMediator getHighCardMediator() {
    return this.highCardMediator;
  }
  
  @Override
  public void setHighCardMediator(HighCardMediator highCardMediator) {
    this.highCardMediator = highCardMediator;
  }
  
  private void updateLifeView(String text) {
    this.lifeView.updateOrCreateTextViewFX(getLayout(), GameStyleClass.LIFE.getAsStyleClass(), text);
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
    EventBus.getDefault().post(new WinnerEvent(game.getCards(), game.getPlayerDetails(), this.getLayout()));
  }
  
  private LifeView getLifeView() {
    return this.lifeView;
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
}
