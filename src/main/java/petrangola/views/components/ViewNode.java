package main.java.petrangola.views.components;

import main.java.petrangola.views.components.position.Placeable;

public interface ViewNode<E> extends Placeable {
  /**
   *
   * @return
   */
  E get();
}
