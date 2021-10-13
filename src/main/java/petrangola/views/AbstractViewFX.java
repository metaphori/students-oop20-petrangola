package main.java.petrangola.views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.utlis.position.Position;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.components.layout.LayoutBuilderImpl;

public abstract class AbstractViewFX extends Group {
  private final LayoutBuilder layoutBuilder;
  private final Stage stage;
  private final Pane layout;
  
  public AbstractViewFX(final Stage stage, final Pane layout, Position[] positions) {
    this.stage = stage;
    this.layout = layout;
    
    this.stage.setResizable(true);
    this.stage.setScene(new Scene(this, ViewConstants.WIDTH.getLength(), ViewConstants.HEIGHT.getLength()));
    this.stage.setWidth(getScene().getWidth());
    this.stage.setHeight(getScene().getHeight());
    this.stage.show();
    
    this.setNeedsLayout(true);
    
    getLayout().prefWidthProperty().bind(getScene().widthProperty());
    getLayout().prefHeightProperty().bind(getScene().heightProperty());
    
    getRoot().getChildren().clear();
    getRoot().getChildren().addAll(getLayout());
    
    this.layoutBuilder = new LayoutBuilderImpl(getLayout(), positions);
  }
  
  public Group getRoot() {
    return (Group) this.stage.getScene().getRoot();
  }
  
  public Pane getLayout() {
    return this.layout;
  }
  
  public LayoutBuilder getLayoutBuilder() {
    return this.layoutBuilder;
  }
}
