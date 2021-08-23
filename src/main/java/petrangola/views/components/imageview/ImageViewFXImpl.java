package main.java.petrangola.views.components.imageview;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.java.petrangola.views.components.ViewNodeImpl;
import main.java.petrangola.views.components.hierarchy.Parent;

public class ImageViewFXImpl extends ViewNodeImpl<ImageView> implements ImageViewFX {
  private Parent<Pane> parent;
  
  public ImageViewFXImpl(ImageView node) {
    super(node);
  }
}
