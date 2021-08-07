package petrangola.controllers.game;

import petrangola.controllers.Controller;

public interface GameController extends Controller {
  /**
   *
   */
  void createPlayers();
  
  /**
   *
   */
  void createBoard();
  
  /**
   *
   */
  void createPlayerDetails();
  
  /**
   *
   */
  void createHighCards();
  
  /**
   *
   */
  void setWinner();
  
  /**
   *
   * @return
   */
  boolean checkKnocks();
  
}
