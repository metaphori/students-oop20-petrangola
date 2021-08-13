package main.java.petrangola.views.components.layout;

import javafx.scene.layout.Pane;

public interface LayoutBuilder {
  
  /**
   *
   * @param spacing
   * @param isCentered
   * @return
   */
  LayoutBuilder addVBox(double spacing, boolean isCentered);
  
  /**
   *
   * @param spacing
   * @return
   */
  LayoutBuilder addHBox(double spacing);
  
  /**
   *
   * @param spacing
   * @return
   */
  LayoutBuilder addHBoxForEach(double spacing);
  
  Pane getLayout();
}
