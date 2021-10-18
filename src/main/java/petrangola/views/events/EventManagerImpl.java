package main.java.petrangola.views.events;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.cards.Combination;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.game.RankingView;
import main.java.petrangola.views.game.RankingViewImpl;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.List;

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
    List<Pair<String, Combination>> bestCombinations = event.getBestCombinations();
    Collections.reverse(bestCombinations);
    
    event.takeLifeIfPossible();
    event.giveLifeIfPossible();
    
    final RankingView rankingView = new RankingViewImpl(new TableView<>());
    rankingView.get().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    rankingView.setBestCombinations(bestCombinations);
    rankingView.setPlayerDetails(event.getPlayerDetails());
    rankingView.loadRows();
    
    final Pane pane = (Pane) event.getLayout().lookup(GameStyleClass.RANKING.getAsStyleClass());
    rankingView.get().prefWidthProperty().bind(pane.widthProperty());
    pane.getChildren().clear();
    pane.getChildren().add(rankingView.get());
    
    this.gameController.setWinner(bestCombinations.get(0).getX());
  }
  
  @Override
  public void register() {
    EventBus.getDefault().register(this);
  }
  
  @Override
  public void cleanup() {
    EventBus.getDefault().unregister(this);
  }
}
