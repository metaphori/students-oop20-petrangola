package main.java.petrangola.views.events;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.cards.*;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.game.RankingView;
import main.java.petrangola.views.game.RankingViewImpl;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    final List<Cards> cardsList = event.getCardsList();
    final BestCombinationsComparator comparator = new BestCombinationComparatorImpl();
    
    List<Pair<String, Combination>> bestCombinations = cardsList
                                                         .stream()
                                                         .filter(Cards::isPlayerCards)
                                                         .map(cards -> new Pair<>(cards.getPlayer().get().getUsername(), cards.getCombination()))
                                                         .sorted((o1, o2) ->  comparator.compare(o1.getY().getBest(), o2.getY().getBest()))
                                                         .collect(Collectors.toList());
    
    final RankingView rankingView = new RankingViewImpl(new TableView<>());
    
    rankingView.setBestCombinations(bestCombinations);
    rankingView.loadRows();
    
    final Pane pane = (Pane) event.getLayout().lookup(GameStyleClass.RANKING.getAsStyleClass());
    
    rankingView.get().prefWidthProperty().bind(pane.widthProperty());
    rankingView.get().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    pane.getChildren().clear();
    pane.getChildren().add(rankingView.get());
  
    Collections.reverse(bestCombinations);
    
    final PlayerDetail playerDetail = event.getPlayerDetails()
                                            .stream()
                                            .filter(tempPlayerDetail -> bestCombinations.get(bestCombinations.size() - 1).getX().equals(tempPlayerDetail.getPlayer().getUsername()))
                                            .findFirst()
                                            .get();
    
    event.getPlayerController().looseLife(playerDetail);
    
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
