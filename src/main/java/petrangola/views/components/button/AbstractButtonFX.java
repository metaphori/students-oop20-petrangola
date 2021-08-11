package main.java.petrangola.views.components.button;

import javafx.scene.control.Button;

public abstract class AbstractButtonFX extends Button implements SimpleButton {
  public AbstractButtonFX(String label) {
    super(label);
    this.handleStyle();
    this.setListeners();
  }
}
