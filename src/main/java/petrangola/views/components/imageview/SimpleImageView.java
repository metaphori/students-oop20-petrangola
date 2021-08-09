package main.java.petrangola.views.components.imageview;


import main.java.petrangola.views.components.ViewNode;

public interface SimpleImageView<R> extends ViewNode {
  /**
   *
   * @return the imageView
   */
  R get();
  
  /**
   *
   * @param imageView - to set in the concrete class
   */
  void set(R imageView);
}
