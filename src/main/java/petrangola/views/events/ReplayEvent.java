package main.java.petrangola.views.events;

import javafx.scene.layout.Pane;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.DealerImpl;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.mediator.CardsMediator;
import main.java.petrangola.views.mediator.GameMediator;

import java.util.List;

public class ReplayEvent implements Event {
  public ReplayEvent(final Game game, final GameMediator gameMediator) {
    final Dealer oldDealer = game.getDealer();
    final CardsMediator cardsMediator = gameMediator.getCardsMediator();
    
    this.setWinnerAsDealer(game, gameMediator);
    this.resetGameParam(game);
    this.unregisterViews(cardsMediator.getLayout());
    this.updatePlayerViews(game, cardsMediator, oldDealer);
  }
  
  private void updatePlayerViews(Game game, CardsMediator cardsMediator, Dealer oldDealer) {
    cardsMediator.updatePlayerViews(game, oldDealer);
  }
  
  private void resetGameParam(Game game) {
    game.setCards(List.of());
    game.setRound(0);
    game.setKnockerCount(0);
    game.setWinner("");
    game.setLastKnocker("");
    game.setOnlyOneRound(false);
    game.setCurrentTurnNumber(0);
  }
  
  private void setWinnerAsDealer(Game game, GameMediator gameMediator) {
    game.getPlayers()
          .stream()
          .filter(player -> player.getUsername().equals(game.getWinner()))
          .filter(player -> !player.isDealer())
          .findFirst()
          .ifPresentOrElse(
                player -> {
                  player.setIsDealer(true);
                  game.setDealer(new DealerImpl(player));
                },
                () -> gameMediator.onDealer(game)
          );
  }
  
  private void unregisterViews(Pane pane) {
    // remove winner text view
    final Pane winnerPane = (Pane) pane.lookup(GameStyleClass.WINNER.getAsStyleClass());
    winnerPane.getChildren().clear();
    // remove table view
    final Pane rankingPane = (Pane) pane.lookup(GameStyleClass.RANKING.getAsStyleClass());
    rankingPane.getChildren().clear();
    // remove replay button
    final Pane userActionsPane = (Pane) pane.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
    userActionsPane.getChildren().clear();
  }
}
