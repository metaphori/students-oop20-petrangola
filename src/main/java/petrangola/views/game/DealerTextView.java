package petrangola.views.game;

import petrangola.views.components.text.TextViewFX;

public interface DealerTextView extends TextViewFX {
  /**
   * @param dealerName
   */
  void setCurrentDealerName(String dealerName);
}
