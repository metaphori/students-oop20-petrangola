package main.java.petrangola.views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;

public abstract class AbstractViewFX extends Group {
  private final Stage stage;
  private final Pane layout;
  
  public AbstractViewFX(Stage stage, Pane layout) {
    this.stage = stage;
    this.layout = layout;
    
    this.stage.setWidth(ViewConstants.WIDTH.getLength());
    this.stage.setHeight(ViewConstants.HEIGHT.getLength());
    this.stage.setResizable(false);
    
    this.stage.setScene(new Scene(this));
    this.stage.show();
    this.setNeedsLayout(true);
    
    getLayout().setPrefWidth(getRoot().getScene().getWidth());
    getLayout().setPrefHeight(getRoot().getScene().getHeight());
    
    getRoot().getChildren().addAll(getLayout());
  }
  
  public Group getRoot() {
    return (Group) this.stage.getScene().getRoot();
  }

  public Pane getLayout() {
    return this.layout;
  }
}
