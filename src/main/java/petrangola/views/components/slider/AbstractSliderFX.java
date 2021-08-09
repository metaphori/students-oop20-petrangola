package main.java.petrangola.views.components.slider;

import javafx.scene.control.Slider;

public class AbstractSliderFX extends Slider {
  public AbstractSliderFX(double v, double v1, double v2) {
    super(v, v1, v2);
    init();
  }
  
  private void init() {
    this.setMinorTickCount(0);
    this.setMajorTickUnit(1);
    this.setSnapToTicks(true);
    this.setShowTickMarks(true);
    this.setShowTickLabels(true);
  }
}
