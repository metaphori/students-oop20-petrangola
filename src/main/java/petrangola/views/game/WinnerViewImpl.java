package main.java.petrangola.views.game;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.AbstractComponentFX;

public class WinnerViewImpl extends AbstractComponentFX<Text> implements WinnerView {
  public WinnerViewImpl(Text component) {
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
  public void setListeners() {
  
  }
}
