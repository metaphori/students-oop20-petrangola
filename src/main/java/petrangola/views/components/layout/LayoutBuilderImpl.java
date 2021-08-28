package main.java.petrangola.views.components.layout;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.java.petrangola.utlis.Delimiter;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Position;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.ViewNodeFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LayoutBuilderImpl implements LayoutBuilder {
  
  private final Map<Pos, Pair<Vertical, Horizontal>> positionsMap = new EnumMap<>(Pos.class);
  private final Pane layout;
  private final Position[] layoutPosition;
  
  
  public LayoutBuilderImpl(Pane layout, Position[] layoutPositions) {
    this.layout = layout;
    this.layoutPosition = layoutPositions;
    
    initPositionsMap();
  }
  
  public Map<Pos, Pair<Vertical, Horizontal>> getPositionsMap() {
    return positionsMap;
  }
  
  @Override
  public LayoutBuilder addHBox(List<Pair<? extends Node, String>> childNodes) {
    final HBox hBox = new HBox();
    
    addToLayout(hBox, getChildNodes(childNodes));
    
    return this;
  }
  
  @Override
  public LayoutBuilder addVBox(List<Pair<? extends Node, String>> childNodes) {
    final VBox vBox = (VBox) setGrow(new VBox());
    
    addToLayout(vBox, getChildNodes(childNodes));
    
    return this;
  }
  
  @Override
  public LayoutBuilder addSimplePane(Pair<? extends Node, String> panePair) {
    panePair.getX().getStyleClass().addAll(panePair.getY().split(Delimiter.COMMA.getText()));
    getLayout().getChildren().add(panePair.getX());
    
    return this;
  }
  
  @Override
  public LayoutBuilder addNodeById(String Id, Function<ViewNodeFactory, Node> viewNode) {
    return this;
  }
  
  private void addToLayout(Pane pane, List<? extends Node> childNodes) {
    pane.getChildren().addAll(childNodes);
    getLayout().getChildren().add(pane);
  }
  
  private Node setGrow(Node node) {
    VBox.setVgrow(node, Priority.ALWAYS);
    HBox.setHgrow(node, Priority.ALWAYS);
    
    return node;
  }
  
  private List<? extends Node> getChildNodes(List<Pair<? extends Node, String>> childNodes) {
    return childNodes.stream().map(pair -> {
      final Node node = pair.getX();
      
      node.getStyleClass().addAll(pair.getY().split(Delimiter.COMMA.getText()));
      
      return node;
    }).collect(Collectors.toList());
  }
  
  /*private void initLayout() {
    ObservableList<Node> childNode = this.layout.getChildren();
    
    Arrays.stream(this.layoutPosition)
          .forEachOrdered(position -> {
            final Region region = new Region();
            
            region.setId(Horizontal.CENTER.name().concat(Delimiter.UNDERSCORE.getText()).concat(position.name()));
            
            HBox.setHgrow(region, Priority.ALWAYS);
            
            childNode.add(region);
          });
  }*/
  
  private void initPositionsMap() {
    Arrays.stream(Vertical.values())
          .forEach(vertical -> Arrays.stream(Horizontal.values())
                                     .forEach(horizontal -> {
                                       String posString = vertical.name().concat(Delimiter.UNDERSCORE.getText()).concat(horizontal.name());
                  
                                       if (vertical.name().equals(horizontal.name())) {
                                         posString = vertical.name();
                    
                                       }
                  
                                       final Pos pos = Pos.valueOf(posString);
                  
                                       positionsMap.put(pos, new Pair<>(vertical, horizontal));
                                     }));
  }
  
  @Override
  public Pos getPos(final Pair<Vertical, Horizontal> pair) {
    Optional<Map.Entry<Pos, Pair<Vertical, Horizontal>>> optionalEntry = this.positionsMap
                                                                               .entrySet()
                                                                               .stream()
                                                                               .filter(entry -> entry.getValue().equals(pair))
                                                                               .findFirst();
    
    if (optionalEntry.isEmpty()) {
      throw new IllegalStateException();
    }
    
    return optionalEntry.get().getKey();
  }
  
  
  @Override
  public Pane getLayout() {
    return this.layout;
  }
}
