package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
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
  
  private CardsMediator cardsMediator;
  private DealerMediator dealerMediator;
  private PlayerMediator playerMediator;
  private HighCardMediator highCardmediator;
  private PlayerDetailMediator playerDetailMediator;
  
  private final DealerAnimation dealerAnimation;
  
  public GameMediatorImpl(LayoutBuilder layoutBuilder, GameController gameController) {
    this.layoutBuilder = layoutBuilder;
    this.gameController = gameController;
    this.dealerAnimation = new DealerAnimationImpl(this.layoutBuilder.getLayout());
  }
  
  @Override
  public void onDealer(Dealer dealer) {
    getGameController().setTurnNumbers();
    getCurrentPlayer().setPlayer(dealer);
    getDealerAnimation().showDealerName();
  }
  
  @Override
  public void onRound(int round) {
    if (!currentPlayer.getPlayer().isDealer()) {
      this.dealerMediator.hideDealerView(getLayout());
    }
    
    if (round == 1) {
      this.cardsMediator.showBoardCards();
      this.cardsMediator.showUserCards();
    }
  
    updateRoundTextView(String.valueOf(round));
  }
  
  @Override
  public void onCurrentTurnNumber(Player player) {
    getCurrentPlayer().setPlayer(player);
    this.cardsMediator.setCurrentPlayerCards(getCurrentPlayer());
    this.cardsMediator.toggleUserButton(getCurrentPlayer());
    this.updateUsernameView(getCurrentPlayer().getPlayer().getUsername());
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
  }
  
  @Override
  public void onOnlyOneRound(Game game) {
    if (this.gameController.isLastPlayerTurn()) {
      this.launchWinnerEvent(game);
    }
  }
  
  @Override
  public void setHighCardMediator(HighCardMediator highCardMediator) {
    this.highCardmediator = highCardMediator;
  }
  
  @Override
  public void setCardsMediator(CardsMediator cardsMediator) {
    this.cardsMediator = cardsMediator;
  }
  
  @Override
  public void setDealerMediator(DealerMediator dealerMediator) {
    this.dealerMediator = dealerMediator;
  }
  
  @Override
  public void setPlayerMediator(PlayerMediator playerMediator) {
    this.playerMediator = playerMediator;
  }
  
  @Override
  public void setPlayerDetailMediator(PlayerDetailMediator playerDetailMediator) {
    this.playerDetailMediator = playerDetailMediator;
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
    switch (propertyName) {
      case "playerDetails":
        // No inspection uncheck for the sole reason that I know for sure the source is a List<PlayerDetails>
        //noinspection unchecked
        this.getDealerAnimation().setPlayerDetails((List<PlayerDetail>) source);
        
        break;
      case "board":
        this.getDealerAnimation().setBoard((Board) source);
        break;
      case "dealer":
        this.onDealer((Dealer) source);
        break;
      case "round":
        this.onRound(((Game) source).getRound());
        break;
      case "currentTurnNumber":
        this.onCurrentTurnNumber((Player) source);
        break;
      case "winner":
        this.onWinner(((Game) source).getWinner());
        break;
    }
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
  
  public LifeView getLifeView() {
    return this.lifeView;
  }
  
  public UsernameView getUsernameView() {
    return this.usernameView;
  }
  
  public RoundView getRoundView() {
    return this.roundView;
  }
  
  public WinnerView getWinnerView() {
    return this.winnerView;
  }
  
  public KnockView getKnockView() {
    return this.knockView;
  }
}
