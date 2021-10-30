package petrangola.views.player;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import petrangola.views.components.text.TextViewImpl;

public class LifeViewImpl extends TextViewImpl implements LifeView {
  private static final String TEXT = "Lives: ";
  
  public LifeViewImpl(final Text component) {
    super(component);
    this.get().setFont(Font.font("Arial Black", FontWeight.EXTRA_LIGHT, 24));
  }
  
  @Override
  public void updateOrCreateTextViewFX(Pane layout, String styleClass, String text) {
    super.updateOrCreateTextViewFX(layout, styleClass, TEXT.concat(text));
  }
}
