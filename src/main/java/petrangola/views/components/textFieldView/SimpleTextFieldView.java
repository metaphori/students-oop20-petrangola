package petrangola.views.components.textFieldView;


public interface SimpleTextFieldView<R> {
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
