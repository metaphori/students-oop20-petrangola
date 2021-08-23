package main.java.petrangola.views.game;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.text.TextViewImpl;

public class KnockViewImpl extends TextViewImpl implements KnockView {
  public KnockViewImpl(Text component) {
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
  public void showNumberOfKnocks() {
    this.get().setVisible(true);
  }
}
