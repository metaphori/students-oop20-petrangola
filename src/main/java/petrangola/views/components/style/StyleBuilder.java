package main.java.petrangola.views.components.style;

import main.java.petrangola.utlis.UserAction;

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
