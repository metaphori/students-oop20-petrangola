package petrangola.controllers.action;

import petrangola.controllers.MenuController;

public interface ActionController extends MenuController {
  /**
   *
   */
  void start();
  
  /**
   *
   */
  void quit();
}
