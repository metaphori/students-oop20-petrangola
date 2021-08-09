package main.java.petrangola.views.components.textView;

import main.java.petrangola.views.components.ViewNode;

public interface SimpleTextView<R> extends ViewNode {
  /**
   *
   * @return
   */
  R getValue();
  
  /**
   *
   */
  void setValue(R value);
}
