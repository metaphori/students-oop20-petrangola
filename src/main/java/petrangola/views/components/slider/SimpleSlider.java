package petrangola.views.components.slider;


public interface SimpleSlider<T> {
  /**
   * @return
   */
  T getValueFromSlider();
  
  /**
   * @param value
   */
  void setValueFromSlider(T value);
}
