package petrangola.views.components.text;

import petrangola.views.components.ViewNode;

public interface TextView<E> extends ViewNode<E> {
  /**
   *
   * @return
   */
  String getText();
  
  /**
   *
   * @param text
   */
  void setText(String text);
}
