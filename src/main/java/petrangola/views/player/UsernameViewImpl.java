package petrangola.views.player;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import petrangola.views.components.text.TextViewImpl;

public class UsernameViewImpl extends TextViewImpl implements UsernameView {
  private static final String TEXT = "Current Player : ";
  
  public UsernameViewImpl(final Text component) {
    super(component);
    
    this.get().setTextAlignment(TextAlignment.CENTER);
    this.get().setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
  }
  
  @Override
  public void updateOrCreateTextViewFX(Pane layout, String styleClass, String text) {
    super.updateOrCreateTextViewFX(layout, styleClass, TEXT.concat(text));
  }
}
