package main.java.petrangola.views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.components.layout.LayoutBuilderImpl;

import java.util.Objects;

public abstract class AbstractViewFX extends Group {
  private final LayoutBuilder layoutBuilder;
  private final Stage stage;
  private final Pane layout;
  
  public AbstractViewFX(final Stage stage, final Pane layout) {
    this.stage = stage;
    this.layout = layout;
    
    this.setNeedsLayout(true);
    
    this.getStage().setResizable(true);
    this.getStage().setScene(new Scene(this, ViewConstants.WIDTH.getLength(), ViewConstants.HEIGHT.getLength()));
    this.getStage().setWidth(this.getScene().getWidth());
    this.getStage().setHeight(this.getScene().getHeight());
    this.getStage().show();
    this.getStage().minWidthProperty().bind(this.getScene().heightProperty().divide(1.5));
    this.getStage().minHeightProperty().bind(this.getScene().widthProperty().divide(1.5));
    
    this.getLayout().prefWidthProperty().bind(this.getScene().widthProperty());
    this.getLayout().prefHeightProperty().bind(this.getScene().heightProperty());
    
    ViewFX.addOrUpdate(this.getRoot(), this.getLayout());
    
    this.layoutBuilder = new LayoutBuilderImpl(this.getLayout());
  }
  
  public Stage getStage() {
    return this.stage;
  }
  
  public Group getRoot() {
    return (Group) this.getStage().getScene().getRoot();
  }
  
  public Pane getLayout() {
    return this.layout;
  }
  
  public LayoutBuilder getLayoutBuilder() {
    return this.layoutBuilder;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractViewFX)) return false;
    AbstractViewFX that = (AbstractViewFX) o;
    return getLayoutBuilder().equals(that.getLayoutBuilder()) && getStage().equals(that.getStage()) && getLayout().equals(that.getLayout());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getLayoutBuilder(), getStage(), getLayout());
  }
}
