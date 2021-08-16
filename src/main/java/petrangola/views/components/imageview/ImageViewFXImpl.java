package main.java.petrangola.views.components.imageview;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.java.petrangola.views.components.hierarchy.Parent;
import main.java.petrangola.views.components.ViewNodeImpl;

public class ImageViewFXImpl extends ViewNodeImpl<ImageView> implements ImageViewFX {
  private Parent<Pane> parent;
  
  @Override
  public Parent<Pane> getParent() {
    return this.parent;
  }
  
  @Override
  public void setParent(Parent<Pane> parent) {
    this.parent = parent;
  }
}
