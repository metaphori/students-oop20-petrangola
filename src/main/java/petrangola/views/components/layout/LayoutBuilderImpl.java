package main.java.petrangola.views.components.layout;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.components.ViewNode;

import java.util.Arrays;
import java.util.Optional;

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
  
  public void addToNestedPane(ViewNode viewNode, Vertical position, int depth, Optional<Integer> levelIndex) {
    Pane pane = getLayout();
  
    for (int i = 0; i < depth; i++) {
      pane = (Pane) pane.getChildren();
    }
  
    pane.getChildren()
          .stream()
          .filter(node -> node.getId().equals(position.name()))
          .map(node -> (Group) node)
          .findFirst()
          .ifPresent(value -> value.getChildren().add((Node) viewNode));
  }
  
  private Pane addLayoutPositions(Pane pane) {
    Arrays.stream(Vertical.values())
          .forEach(layoutPosition -> {
            final Group group = new Group();
            group.setId(layoutPosition.name());
            pane.getChildren().add(group);
          });
    
    return pane;
  }
  
  private void addNode(Pane pane) {
    if (this.pane == null) {
      this.pane = pane;
    } else {
      this.pane.getChildren().add(addLayoutPositions(pane));
    }
  }
}
