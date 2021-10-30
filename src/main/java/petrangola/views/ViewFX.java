package petrangola.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ViewFX {
  static void addOrUpdate(Pane pane, Node node) {
    pane.getChildren().removeIf(childNode -> childNode.equals(node));
    pane.getChildren().add(node);
  }
  
  static void addOrUpdate(Group group, Node node) {
    group.getChildren().removeIf(childNode -> childNode.equals(node));
    group.getChildren().add(node);
  }
  
  static void addOrUpdateAll(Pane pane, Node... nodes) {
    Arrays.stream(nodes).forEach(node -> addOrUpdate(pane, node));
  }
  
  static void addOrUpdateAll(Pane pane, ObservableList<Node> nodes) {
    final ObservableList<Node> tempNodes = FXCollections.observableArrayList(nodes);
    tempNodes.forEach(node -> addOrUpdate(pane, node));
  }
  
  static void addOrUpdateAll(Pane pane, List<? extends Pane> childrenPanes) {
    final List<? extends Pane> tempChildrenPanes = new ArrayList<>(childrenPanes);
    tempChildrenPanes.forEach(childPane -> addOrUpdate(pane, childPane));
  }
}