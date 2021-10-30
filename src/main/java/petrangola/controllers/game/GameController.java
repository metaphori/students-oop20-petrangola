package petrangola.controllers.game;

import petrangola.controllers.ViewableController;
import petrangola.models.player.PlayerFactory;
import petrangola.utlis.DifficultyLevel;

public interface GameController extends ViewableController {
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
