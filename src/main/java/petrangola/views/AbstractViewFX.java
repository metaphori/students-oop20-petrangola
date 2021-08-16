package main.java.petrangola.views;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.AbstractComponentFX;
import main.java.petrangola.views.components.ViewNode;
import main.java.petrangola.views.components.button.SimpleButton;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractViewFX extends Group {
  private final Stage stage;
  private final Pane layout;
  
  public AbstractViewFX(final Stage stage, final Pane layout) {
    this.stage = stage;
    this.layout = layout;
    
    this.stage.setResizable(true);
    this.stage.setScene(new Scene(this, ViewConstants.WIDTH.getLength(), ViewConstants.HEIGHT.getLength()));
    this.stage.setWidth(getScene().getWidth());
    this.stage.setHeight(getScene().getHeight());
    this.stage.show();
    
    this.setNeedsLayout(true);
    
    getLayout().setPrefWidth(getRoot().getScene().getWidth());
    getLayout().setPrefHeight(getRoot().getScene().getHeight());
    
    getRoot().getChildren().clear();
    getRoot().getChildren().addAll(getLayout());
  }
  
  public final void loadChildren(Field[] declaredFields) {
    Arrays.stream(declaredFields).forEach(field -> {
        
        if (!AbstractComponentFX.class.isAssignableFrom(field.getType())) {
          return;
        }
  
      try {
        field.setAccessible(true);
        
        final AbstractComponentFX<?> component = (AbstractComponentFX<?>) field.get(this);
        getLayout().getChildren().add((Node) component.get());
        
        
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
