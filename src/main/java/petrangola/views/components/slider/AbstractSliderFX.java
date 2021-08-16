package main.java.petrangola.views.components.slider;

import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import main.java.petrangola.views.components.AbstractComponentFX;

public abstract class AbstractSliderFX extends AbstractComponentFX<Slider> {
  public AbstractSliderFX(double v, double v1, double v2) {
    super(new Slider(v,v1,v2));
    init();
  }
  
  private void init() {
    super.get().setMinorTickCount(0);
    super.get().setMajorTickUnit(1f);
    super.get().setBlockIncrement(1f);
    super.get().setSnapToTicks(true);
    super.get().setShowTickMarks(true);
    super.get().setShowTickLabels(true);
    // Avoid any kind of mouse event, only arrow key events are allowed
    EventHandler<MouseEvent> handler = MouseEvent::consume;
    super.get().addEventFilter(MouseEvent.ANY, handler);
  }
}
