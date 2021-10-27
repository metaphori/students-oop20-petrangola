package main.java.petrangola.views.components.textFieldView;

import javafx.scene.control.TextField;
import main.java.petrangola.views.components.AbstractComponentFX;

import java.util.Objects;

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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractTextField)) return false;
    if (!super.equals(o)) return false;
    AbstractTextField that = (AbstractTextField) o;
    return Objects.equals(text, that.text);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), text);
  }
}
