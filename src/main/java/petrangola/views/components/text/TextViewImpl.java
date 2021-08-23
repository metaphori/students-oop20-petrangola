package main.java.petrangola.views.components.text;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.AbstractComponentFX;

public class TextViewImpl extends AbstractComponentFX<Text> implements TextViewFX {
  public TextViewImpl(Text component) {
    super(component);
  }
  
  @Override
  public void setListeners() {
    // empty
  }
  
  @Override
  public String getText() {
    return this.get().getText();
  }
  
  @Override
  public void setText(String text) {
    this.get().setText(text);
  }
}
