package main.java.petrangola.views;

import main.java.petrangola.models.option.Option;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.option.OptionView;

public interface ViewFactory {
  /**
   *
   * @return
   * @param option
   */
  GameView createGameView(final Option option);
  
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
