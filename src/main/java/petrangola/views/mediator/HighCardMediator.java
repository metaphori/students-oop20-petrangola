package petrangola.views.mediator;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import petrangola.models.player.PlayerDetail;

import java.util.List;

public interface HighCardMediator extends Mediator {
  /**
   *
   * @param layout
   * @return
   */
  List<FlowPane> getNPCHighCardPanes(Pane layout);
  
  /**
   *
   * @param layout
   * @return
   */
  Pane getUserHighCardPane(Pane layout);
  
  /**
   *
   * @param playersDetails
   */
  void setPlayersDetails(List<PlayerDetail> playersDetails);
}
