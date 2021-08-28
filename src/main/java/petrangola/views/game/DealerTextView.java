package main.java.petrangola.views.game;

import main.java.petrangola.views.components.text.TextViewFX;

public interface DealerTextView extends TextViewFX {
  /**
   * @param dealerName
   */
  void setCurrentDealerName(String dealerName);
}
