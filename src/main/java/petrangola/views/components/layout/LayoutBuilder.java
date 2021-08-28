package main.java.petrangola.views.components.layout;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.ViewNodeFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface LayoutBuilder {
  Map<Pos, Pair<Vertical, Horizontal>> getPositionsMap();
  
  LayoutBuilder addHBox(List<Pair<? extends Node, String>> childNodes);
  
  LayoutBuilder addVBox(List<Pair<? extends Node, String>> childNodes);
  
  LayoutBuilder addSimplePane(Pair<? extends Node, String> panePair);
  
  LayoutBuilder addNodeById(String Id, Function<ViewNodeFactory, Node> viewNode);
  
  /**
   * @return
   */
  Pane getLayout();
  
  /**
   * @param pair
   * @return
   */
  Pos getPos(final Pair<Vertical, Horizontal> pair);
  
  default Pair<? extends Node, String> addPair(Node node, String Id) {
    return new Pair<>(node, Id);
  }
}
