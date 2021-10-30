package petrangola.controllers.option;

import petrangola.controllers.MenuController;
import petrangola.models.option.Option;
import petrangola.utlis.DifficultyLevel;

public interface OptionController extends MenuController {
  /**
   * @param opponentsSize
   */
  void setOpponentsSize(int opponentsSize);
  
  /**
   * @param difficulty
   */
  void setDifficulty(DifficultyLevel difficulty);
  
  /**
   * @param username
   */
  void setUsername(String username);
  
  /**
   *
   */
  void play(final Option option);
}
