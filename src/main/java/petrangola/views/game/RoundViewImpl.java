package main.java.petrangola.views.game;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.AbstractComponentFX;

public class RoundViewImpl extends AbstractComponentFX<Text> implements RoundView {
  public RoundViewImpl(Text component) {
    super(component);
  }
  
  @Override
  public String getValue() {
    return super.get().getText();
  }
  
  @Override
  public void setValue(String value) {
    super.get().setText(value);
  }
  
  @Override
  public void showRoundNumber() {
  
  }
  
  @Override
  public void setListeners() {
  
  }
}
