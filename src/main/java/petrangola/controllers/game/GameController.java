package main.java.petrangola.controllers.game;

import main.java.petrangola.controllers.Controller;

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
