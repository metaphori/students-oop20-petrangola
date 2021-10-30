package petrangola.views.components.button;

import petrangola.dto.DTO;
import petrangola.views.components.ViewNode;

public interface SimpleButton<E> extends ViewNode<E> {
  /**
   *
   *  This method is used to handle style of a button
   */
  void handleStyle();
  
  /**
   *
   * @param data
   */
  void setData(DTO data);
  
  /**
   *
   * @param isDisabled
   */
  void setDisable(boolean isDisabled);
}
