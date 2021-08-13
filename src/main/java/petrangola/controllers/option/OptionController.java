package main.java.petrangola.controllers.option;

import main.java.petrangola.controllers.MenuController;
import main.java.petrangola.utlis.DifficultyLevel;

public interface OptionController extends MenuController {
  /**
   *
   * @param opponentsSize
   */
  void setOpponentsSize(int opponentsSize);
  
  /**
   *
   * @param difficulty
   */
  void setDifficulty(DifficultyLevel difficulty);
  
  /**
   *
   * @param username
   */
  void setUsername(String username);
  
  /**
   *
   */
  void play(final Object... data);
}
