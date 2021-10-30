package petrangola.views;

import petrangola.models.player.PlayerDetail;
import petrangola.views.board.BoardView;
import petrangola.views.player.DealerView;
import petrangola.views.player.NPCView;
import petrangola.views.player.UserView;

public interface GameObjectViewFactory {
  /**
   *
   * @param playerDetail
   * @return
   */
  NPCView createNPCView(final PlayerDetail playerDetail);
  
  /**
   *
   * @param playerDetail
   * @return
   */
  UserView createUserView(final PlayerDetail playerDetail);
  
  /**
   *
   * @param playerDetail
   * @return
   */
  DealerView createDealerView(final PlayerDetail playerDetail);
  
  /**
   *
   * @return
   */
  BoardView createBoardView();
}
