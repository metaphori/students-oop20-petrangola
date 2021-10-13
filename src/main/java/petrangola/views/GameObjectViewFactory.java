package main.java.petrangola.views;

import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.player.DealerView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.UserView;

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
