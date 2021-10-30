package petrangola.views.animation.player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import petrangola.controllers.player.DealerController;
import petrangola.models.board.Board;
import petrangola.models.player.PlayerDetail;
import petrangola.views.animation.Animation;
import petrangola.views.mediator.HighCardMediator;

import java.util.List;

public interface DealerAnimation extends Animation {
  /**
   *
   * @return
   */
  EventHandler<ActionEvent> showDealerName();
  
  /**
   *
   * @param highCardMediator
   * @return
   */
  EventHandler<ActionEvent> hideHighCards(HighCardMediator highCardMediator);
  
  /**
   *
   * @return
   */
  EventHandler<ActionEvent> dealCards();
  
  /**
   *
   * @param dealerController
   */
  void setDealerController(DealerController dealerController);
  
  /**
   *
   * @param playersDetails
   */
  void setPlayersDetails(List<PlayerDetail> playersDetails);
  
  /**
   *
   * @param board
   */
  void setBoard(Board board);
  
  /**
   *
   */
  void cleanUp();
}
