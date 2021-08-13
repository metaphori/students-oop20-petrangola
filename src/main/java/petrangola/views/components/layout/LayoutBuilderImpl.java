package main.java.petrangola.views.components.layout;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LayoutBuilderImpl implements LayoutBuilder {
  private Pane pane = null;
  
  public LayoutBuilderImpl() { }
  
  @Override
  public LayoutBuilder addVBox(double spacing, boolean isCentered) {
    final VBox vBox = new VBox(spacing);
    
    if (isCentered) {
      vBox.setAlignment(Pos.CENTER);
    }
    
    this.addNode(vBox);
    
    return this;
  }
  
  @Override
  public LayoutBuilder addHBox(double spacing) {
    this.addNode(new HBox(spacing));
    return this;
  }
  
  @Override
  public LayoutBuilder addHBoxForEach(double spacing) {
    this.pane
          .getChildren()
          .stream()
          .map(node -> (Pane) node)
          .forEachOrdered(pane -> pane.getChildren().add(new HBox(spacing)));
    
    return this;
  }
  
  @Override
  public Pane getLayout() {
    return this.pane;
  }
  
  private void addNode(Pane pane) {
    if (this.pane == null) {
      this.pane = pane;
    } else {
      this.pane.getChildren().add(pane);
    }
  }
}
