package main.java.petrangola.views.game;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.java.petrangola.views.components.text.TextViewImpl;

public class RoundViewImpl extends TextViewImpl implements RoundView {
  private static final String TEXT = "Round n. ";
  
  public RoundViewImpl(Text component) {
    super(component);
    this.get().setFont(Font.font("Arial Black", FontWeight.BOLD, 32));
  }
  
  @Override
  public void updateOrCreateTextViewFX(Pane layout, String styleClass, String text) {
    super.updateOrCreateTextViewFX(layout, styleClass, TEXT.concat(text));
  }
}
