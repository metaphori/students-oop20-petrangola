package main.java.petrangola.views.components.slider;

import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class AbstractSliderFX extends Slider {
  public AbstractSliderFX(double v, double v1, double v2) {
    super(v, v1, v2);
    init();
  }
  
  private void init() {
    this.setMinorTickCount(0);
    this.setMajorTickUnit(1f);
    this.setBlockIncrement(1f);
    this.setSnapToTicks(true);
    this.setShowTickMarks(true);
    this.setShowTickLabels(true);
    // Avoid any kind of mouse event, only arrow key events are allowed
    EventHandler<MouseEvent> handler = MouseEvent::consume;
    this.addEventFilter(MouseEvent.ANY, handler);
  }
}
