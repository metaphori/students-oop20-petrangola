package main.java.petrangola.views;

import main.java.petrangola.models.option.Option;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.option.OptionView;

public interface ViewFactory {
  /**
   *
   * @param option
   */
  void createGameView(final Option option);
  
  /**
   *
   */
  void createOptionView();
  
  /**
   *
   */
  void createActionView();
}
