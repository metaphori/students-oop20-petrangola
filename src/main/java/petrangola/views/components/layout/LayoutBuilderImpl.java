package main.java.petrangola.views.components.layout;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import main.java.petrangola.utlis.Delimiter;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Position;
import main.java.petrangola.utlis.position.Vertical;

import java.util.*;
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
  public LayoutBuilder addHBox(List<Pair<? extends Pane, String>> childNodes) {
    final HBox hBox = new HBox();
    
    addToLayout(hBox, getChildNodes(childNodes));
    
    return this;
  }
  
  @Override
  public LayoutBuilder addVBox(List<Pair<? extends Pane, String>> childNodes) {
    final VBox vBox = (VBox) setGrow(new VBox());
    
    addToLayout(vBox, getChildNodes(childNodes));
    
    return this;
  }
  
  @Override
  public LayoutBuilder addSimplePane(Pair<? extends Pane, String> panePair) {
    panePair.getX().getStyleClass().addAll(panePair.getY().split(Delimiter.COMMA.getText()));
    
    getLayout().getChildren().add(panePair.getX());
    
    return this;
  }
  
  @Override
  public LayoutBuilder addNode(Node node) {
    getLayout().getChildren().add(node);
    
    return this;
  }
  
  @Override
  public LayoutBuilder addNodeById(String Id, Node node) {
    Pane pane = (Pane) getLayout().lookup(Id);
    
    pane.getChildren().add(node);
    
    return this;
  }
  
  
  private void addToLayout(Pane pane, List<? extends Pane> childNodes) {
    pane.getChildren().addAll(childNodes);
    getLayout().getChildren().add(pane);
  }
  
  private Node setGrow(Node node) {
    VBox.setVgrow(node, Priority.ALWAYS);
    HBox.setHgrow(node, Priority.ALWAYS);
    
    return node;
  }
  
  private List<? extends Pane> getChildNodes(List<Pair<? extends Pane, String>> childNodes) {
    return childNodes.stream().map(pair -> {
      final String[] styleClasses = pair.getY().split(Delimiter.COMMA.getText());
      
      List<Pane> children = Arrays.stream(styleClasses).map(styleClass -> {
        final StackPane childPane = new StackPane();
        
        setGrow(childPane);
        
        childPane.getStyleClass().add(styleClass);
        
        return childPane;
      }).collect(Collectors.toList());
      
      pair.getX().getChildren().addAll(children);
      
      return pair.getX();
    }).collect(Collectors.toList());
  }
  
  
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
