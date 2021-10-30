package petrangola.views.components.style;

import petrangola.utlis.UserAction;

public interface StyleBuilder {
  /**
   * @param style
   * @param action
   * @return
   */
  StyleBuilder addStyle(String style, UserAction action);
  
  /**
   * @param action
   * @return
   */
  String getStyles(UserAction action);
}
