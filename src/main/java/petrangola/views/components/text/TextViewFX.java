package petrangola.views.components.text;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import petrangola.views.ViewFX;

public interface TextViewFX extends TextView<Text> {
  /**
   *
   */
  void show();
  
  /**
   *
   */
  void hide();
  
  default void updateOrCreateTextViewFX(Pane layout, String styleClass, String text) {
    this.setText(text);
    this.show();
    
    final Pane pane = (Pane) layout.lookup(styleClass);
    ViewFX.addOrUpdate(pane, this.get());
  }
}
