package main.java.petrangola.views.components.table;

import main.java.petrangola.views.components.ViewNode;

import java.util.List;

public interface Table<E, T> extends ViewNode<T> {
  /**
   *
   * @param list
   */
  void addRows(List<E> list);
}
