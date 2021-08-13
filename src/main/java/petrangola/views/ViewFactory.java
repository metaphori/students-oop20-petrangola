package main.java.petrangola.views;

import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.option.OptionView;

public interface ViewFactory {
  /**
   *
   * @return
   * @param username
   * @param difficultyLevel
   * @param opponentsSize
   */
  GameView createGameView(String username, DifficultyLevel difficultyLevel, int opponentsSize);
  
  /**
   *
   * @return
   */
  OptionView createOptionView();
  
  /**
   *
   * @return
   */
  ActionView createActionView();
}
