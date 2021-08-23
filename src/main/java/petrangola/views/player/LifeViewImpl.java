package main.java.petrangola.views.player;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.text.TextViewImpl;

public class LifeViewImpl extends TextViewImpl implements LifeView {
  private static final String TEXT = "Vite: ";
  
  public LifeViewImpl(Text component) {
    super(component);
  }
  
  
  @Override
  public void setLives(int currentAmount) {
    this.setText(TEXT.concat(String.valueOf(currentAmount)));
  }
}
