package petrangola.views;

import petrangola.controllers.Controller;

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
