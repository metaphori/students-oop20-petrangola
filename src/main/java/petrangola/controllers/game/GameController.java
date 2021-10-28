package main.java.petrangola.controllers.game;

import main.java.petrangola.controllers.ViewController;
import main.java.petrangola.models.player.PlayerFactory;
import main.java.petrangola.utlis.DifficultyLevel;

public interface GameController extends ViewController {
  /**
   * @param playerFactory
   */
  void setPlayerFactory(PlayerFactory playerFactory);
  
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
  void createPlayersDetails();
  
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
   * @param winner
   */
  void setWinner(String winner);
  
  /**
   * @return
   */
  boolean checkKnocks();
  
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
   * @param username
   */
  void addKnock(String username);
  
  /**
   * @return
   */
  boolean isLastKnockerPlayerTurn();
  
  /**
   * @return
   */
  boolean isLastPlayerTurn();
}
