package main.java.petrangola.views.components.textFieldView;

import javafx.scene.control.TextField;
import main.java.petrangola.views.components.AbstractComponentFX;

public abstract class AbstractTextField extends AbstractComponentFX<TextField> {
  private String text;
  
  public AbstractTextField(TextField component) {
    super(component);
  }
  
  public String getValue() {
    return this.text;
  }
  
  public void setValue(String value) {
    this.text = value;
  }
}
