package main.java.petrangola.views.player;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.petrangola.views.components.text.TextViewImpl;

public class LifeViewImpl extends TextViewImpl implements LifeView {
  private static final String TEXT = "Vite: ";
  
  public LifeViewImpl(final Text component) {
    super(component);
  }
  
  @Override
  public void updateOrCreateTextViewFX(Pane layout, String styleClass, String text) {
    super.updateOrCreateTextViewFX(layout, styleClass, TEXT.concat(text));
  }
}
