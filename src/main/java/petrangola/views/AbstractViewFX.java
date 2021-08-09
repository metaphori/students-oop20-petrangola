package main.java.petrangola.views;

import javafx.scene.Parent;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;

public class AbstractViewFX extends Parent {
  private final Stage stage;
  
  public AbstractViewFX(Stage stage) {
    this.stage = stage;
    this.stage.setResizable(false);
    this.stage.setWidth(ViewConstants.WIDTH.getLength());
    this.stage.setHeight(ViewConstants.HEIGHT.getLength());
    this.stage.show();
  }
  
  public Stage getStage() {
    return this.stage;
  }
}
