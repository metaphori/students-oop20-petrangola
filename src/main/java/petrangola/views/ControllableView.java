package main.java.petrangola.views;

import main.java.petrangola.controllers.Controller;

public interface ControllableView extends View {
  /**
   *
   * @param controller
   */
  void setController(Controller controller);
  
  /**
   *
   * @param viewFactory
   */
  void initView(ViewFactory viewFactory);
}
