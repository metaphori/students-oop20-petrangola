package petrangola.views.events;

import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import petrangola.controllers.game.GameController;
import petrangola.models.cards.Combination;
import petrangola.utlis.Pair;
import petrangola.views.ViewFX;
import petrangola.views.components.button.AbstractButtonFX;
import petrangola.views.game.GameStyleClass;
import petrangola.views.game.RankingView;
import petrangola.views.game.RankingViewImpl;
import petrangola.views.game.buttons.ReplayButton;
import petrangola.views.mediator.CardsMediator;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

public class EventManagerImpl implements EventManager {
  private final GameController gameController;
  
  public EventManagerImpl(final GameController gameController) {
    this.gameController = gameController;
  }
  
  @Subscribe
  public void onNextRound(NextRoundEvent event) {
    this.gameController.roundHandler();
  }
  
  @Subscribe
  public void onNextTurn(NextTurnEvent event) {
    this.gameController.nextTurnNumberHandler();
  }
  
  @Subscribe
  public void onKnockEvent(KnockEvent event) {
    if (!this.gameController.checkKnocks()) {
      this.gameController.addKnock(event.getPlayer().getUsername());
    }
  }
  
  @Subscribe
  public void onWinnerEvent(WinnerEvent event) {
    final List<Pair<String, Combination>> bestCombinations = event.getBestCombinations();
    final CardsMediator cardsMediator = event.getGameMediator().getCardsMediator();
    
    event.getGameMediator().unregister(cardsMediator.getLayout());
    event.takeLifeIfPossible();
    event.giveLifeIfPossible();
    
    final RankingView rankingView = new RankingViewImpl(new TableView<>());
    rankingView.get().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    rankingView.setBestCombinations(bestCombinations);
    rankingView.setPlayersDetails(event.getPlayersDetails());
    rankingView.loadRows();
    
    final Pane rankingPane = (Pane) cardsMediator.getLayout().lookup(GameStyleClass.RANKING.getAsStyleClass());
    rankingView.get().prefWidthProperty().bind(rankingPane.widthProperty());
    ViewFX.addOrUpdate(rankingPane, rankingView.get());
    
    final Pane userActionsPane = (Pane) cardsMediator.getLayout().lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
    final AbstractButtonFX replayButton = new ReplayButton(event.getGame(), event.getGameMediator());
    final HBox hBox = (HBox) userActionsPane.getParent();
    hBox.setAlignment(Pos.BOTTOM_CENTER);
    ViewFX.addOrUpdate(userActionsPane, replayButton.get());
    
    this.gameController.setWinner(bestCombinations.get(bestCombinations.size() - 1).getX());
  }
  
  @Subscribe
  public void onReplayEvent(ReplayEvent event) {
    // empty
  }
  
  @Override
  public void register() {
    EventBus.getDefault().register(this);
  }
  
  @Override
  public void cleanup() {
    EventBus.getDefault().unregister(this);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventManagerImpl)) return false;
    EventManagerImpl that = (EventManagerImpl) o;
    return gameController.equals(that.gameController);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(gameController);
  }
}
