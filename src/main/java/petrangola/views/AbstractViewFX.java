package main.java.petrangola.views;

import java.lang.reflect.Field;
import java.util.Arrays;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.ViewNode;

public abstract class AbstractViewFX extends Group {
  private final Stage stage;
  private final Pane layout;
  
  public AbstractViewFX(final Stage stage, final Pane layout) {
    this.stage = stage;
    this.layout = layout;
    
    this.stage.setResizable(true);
    this.stage.setScene(new Scene(this, ViewConstants.WIDTH.getLength(),  ViewConstants.HEIGHT.getLength()));
    this.stage.setWidth(getScene().getWidth());
    this.stage.setHeight(getScene().getHeight());
    this.stage.show();
    
    this.setNeedsLayout(true);

    getLayout().setPrefWidth(getRoot().getScene().getWidth());
    getLayout().setPrefHeight(getRoot().getScene().getHeight());
    
    getRoot().getChildren().clear();
    getRoot().getChildren().addAll(getLayout());
  }
  
  @SafeVarargs
  public final void loadChildren(Field[] declaredFields, Class<? extends ViewNode>... interfaces) {
    Arrays.stream(declaredFields).forEach(field -> {
      try {
        if (Arrays.stream(interfaces).noneMatch(singleInterface -> field.getType().equals(singleInterface))) {
          return;
        }
  
        field.setAccessible(true); // Super unsafe to use
        
        getLayout().getChildren().add((Node) field.get(this));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });
  }
  
  public Group getRoot() {
    return (Group) this.stage.getScene().getRoot();
  }

  public Pane getLayout() {
    return this.layout;
  }
}
