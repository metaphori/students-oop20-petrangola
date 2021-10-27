package main.java.petrangola.views.components.layout;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import main.java.petrangola.utlis.Delimiter;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Position;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.ViewFX;

import java.util.*;
import java.util.stream.Collectors;

public class LayoutBuilderImpl implements LayoutBuilder {
  
  private final Map<Pos, Pair<Vertical, Horizontal>> positionsMap = new EnumMap<>(Pos.class);
  private final Pane layout;
  
  
  public LayoutBuilderImpl(Pane layout) {
    this.layout = layout;
    
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
  
    ViewFX.addOrUpdate(getLayout(), panePair.getX());
    
    return this;
  }
  
  @Override
  public LayoutBuilder addNode(Node node) {
    ViewFX.addOrUpdate(getLayout(), node);
    
    return this;
  }
  
  @Override
  public LayoutBuilder addNodeById(String Id, Node node) {
    Pane pane = (Pane) getLayout().lookup(Id);
  
    ViewFX.addOrUpdate(pane, node);
    
    return this;
  }
  
  
  private void addToLayout(Pane pane, List<? extends Pane> childNodes) {
    ViewFX.addOrUpdateAll(pane, childNodes);
    ViewFX.addOrUpdate(getLayout(), pane);
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
  
      ViewFX.addOrUpdateAll(pair.getX(), children);
      
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
    return this.positionsMap
                 .entrySet()
                 .stream()
                 .filter(entry -> entry.getValue().equals(pair))
                 .findFirst()
                 .map(Map.Entry::getKey)
                 .orElse(null);
  }
  
  
  @Override
  public Pane getLayout() {
    return this.layout;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LayoutBuilderImpl)) return false;
    LayoutBuilderImpl that = (LayoutBuilderImpl) o;
    return Objects.equals(getPositionsMap(), that.getPositionsMap()) && getLayout().equals(that.getLayout());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPositionsMap(), getLayout());
  }
}
