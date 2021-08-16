package main.java.petrangola.views.components.textView;


public interface SimpleTextView<R> {
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
