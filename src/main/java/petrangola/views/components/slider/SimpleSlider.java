package main.java.petrangola.views.components.slider;

import main.java.petrangola.views.components.ViewNode;

public interface SimpleSlider<T> extends ViewNode {
  /**
   *
   * @return
   */
  T getValueFromSlider();
  
  /**
   *
   * @param value
   */
  void setValueFromSlider(T value);
}
