package main.java.petrangola.views.components.imageview;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface ImageViewFX extends SimpleImageView<ImageView> {
  /**
   * @param path - [←relative] of the image
   * @return
   */
  default Image createImage(String path) {
    return new Image(path);
  }
}
