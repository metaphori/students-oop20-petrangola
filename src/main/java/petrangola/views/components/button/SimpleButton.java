package main.java.petrangola.views.components.button;

import main.java.petrangola.views.components.ViewNode;

public interface SimpleButton<B> extends ViewNode {
  /**
   *
   *  This method is used to handle all the actions, listeners and behaviour of a button
   */
  void handleActions();
  
}
