package main.java.petrangola.views.player.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.mediator.HighCardMediator;

import java.util.List;

public interface DealerAnimation extends PlayerAnimation {
  
  EventHandler<ActionEvent> showDealerName();
  
  EventHandler<ActionEvent> hideHighCards(HighCardMediator highCardMediator);
  
  EventHandler<ActionEvent> dealCards();
  
  void setDealerController(DealerController dealerController);
  
  void setPlayerDetails(List<PlayerDetail> playersDetails);
  
  void setBoard(Board board);
}
