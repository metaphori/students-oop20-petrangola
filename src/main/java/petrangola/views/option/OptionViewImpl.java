package main.java.petrangola.views.option;

import java.beans.PropertyChangeEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.views.AbstractViewFX;

public class OptionViewImpl extends AbstractViewFX implements OptionView {
  
  public OptionViewImpl(Stage stage) {
    super(stage, new VBox(8));
  }
  
  @Override
  public Pane getLayout() {
    return null;
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
  
  }
}
