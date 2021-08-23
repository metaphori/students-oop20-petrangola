package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.imageview.ImageViewFX;

public interface CardView extends ImageViewFX {
  /**
   * @param player
   * @return
   */
  default boolean isActionDisabled(Player player) {
    return player.isNPC();
  }
  
  /**
   *
   */
  void showCard();
  
  /**
   * @return
   */
  boolean isCovered();
  
  /**
   * @return
   */
  boolean isHidden();
  
  /**
   * @return
   */
  boolean isChosen();
  
  /**
   *
   */
  void toggleChosen();
  
  /**
   * @return
   */
  Card getCard();
  
  void setListeners();
}
