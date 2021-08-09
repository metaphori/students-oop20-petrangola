package main.java.petrangola.views.components.imageview;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.java.petrangola.views.components.pane.PaneComponent;

public interface ImageViewFX extends SimpleImageView<ImageView>, PaneComponent<Pane> {
  /**
   *
   * {@inheritDoc}
   */
  @Override
  ImageView get();
  
  /**
   *
   * {@inheritDoc}
   */
  @Override
  void set(ImageView imageView);
  
  /**
   *
   * @param pane - add the ImageView to parent pane
   */
  @Override
  default void add(Pane pane) {
    pane.getChildren().add(get());
  }
  
  /**
   *
   * @param pane - from the parent pane the ImageView, cancelling also the Image property
   */
  @Override
  default void remove(Pane pane) {
    if (get() != null) {
      pane.getChildren().remove(get());
      get().getImage().cancel();
    }
  }
  
  /**
   *
   * @param path - [←relative] of the image
   * @return
   */
  default Image createImage(String path) {
    return new Image(path);
  }
}
