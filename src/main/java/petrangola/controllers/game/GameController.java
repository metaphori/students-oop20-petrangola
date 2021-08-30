package main.java.petrangola.controllers.game;

import main.java.petrangola.controllers.Controller;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.utlis.DifficultyLevel;

public interface GameController extends Controller {
  /**
   *
   */
  void createPlayers(final String username, final DifficultyLevel level, final int size);
  
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
  void setDealer();
  
  /**
   *
   */
  void setTurnNumbers();
  
  /**
   *
   */
  void setWinner();
  
  /**
   * @return
   */
  boolean checkKnocks();
  
  /**
   * @return
   */
  Player getCurrentPlayer();
  
  /**
   *
   */
  void nextTurnNumberHandler();
  
  /**
   *
   */
  void roundHandler();
  
  /**
   *
   */
  void onlyOneRound();
  
  /**
   *
   * @param username
   */
  void addKnock(String username);
}
