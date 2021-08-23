package main.java.petrangola.views.components.hierarchy;

import main.java.petrangola.views.components.ViewNode;

import java.util.List;

public interface Parent<P> {
  /**
   * @return
   */
  List<ViewNode<P>> getChildren();
  
  /**
   * @param childNode
   */
  void addChildren(ViewNode<P> childNode);
  
  /**
   * @param childNode
   */
  void removeChildren(ViewNode<P> childNode);
}
