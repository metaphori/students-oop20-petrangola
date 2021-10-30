package petrangola.views.components.layout;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import petrangola.utlis.Pair;
import petrangola.utlis.position.Horizontal;
import petrangola.utlis.position.Vertical;

import java.util.List;
import java.util.Map;

public interface LayoutBuilder {
  /**
   *
   * @return
   */
  Map<Pos, Pair<Vertical, Horizontal>> getPositionsMap();
  
  /**
   *
   * @param childNodes
   * @return
   */
  LayoutBuilder addHBox(List<Pair<? extends Pane, String>> childNodes);
  
  /**
   *
   * @param childNodes
   * @return
   */
  LayoutBuilder addVBox(List<Pair<? extends Pane, String>> childNodes);
  
  /**
   *
   * @param panePair
   * @return
   */
  LayoutBuilder addSimplePane(Pair<? extends Pane, String> panePair);
  
  LayoutBuilder addNode(Node node);
  
  /**
   *
   * @param Id
   * @param node
   * @return
   */
  LayoutBuilder addNodeById(String Id, Node node);
  
  /**
   * @return
   */
  Pane getLayout();
  
  /**
   * @param pair
   * @return
   */
  Pos getPos(final Pair<Vertical, Horizontal> pair);
  
  /**
   *
   * @param pane
   * @param Id
   * @return
   */
  default Pair<? extends Pane, String> addPair(Pane pane, String Id) {
    return new Pair<>(pane, Id);
  }
}
