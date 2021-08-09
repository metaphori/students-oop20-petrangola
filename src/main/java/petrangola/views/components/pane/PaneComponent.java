package main.java.petrangola.views.components.pane;


import main.java.petrangola.views.components.ViewNode;

public interface PaneComponent<P> extends ViewNode {
  /**
   *
   * @param pane - add components to pane
   */
  void add(P pane);
  
  /**
   *
   * @param pane - from the parent pane
   */
  void remove(P pane);
}
