package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;

public interface Mediator {
  /**
   * @param layout
   */
  void register(Pane layout);
  
  /**
   * @param layout
   */
  void unregister(Pane layout);
}
