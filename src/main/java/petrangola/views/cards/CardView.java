package petrangola.views.cards;

import petrangola.models.cards.Card;
import petrangola.views.components.imageview.ImageViewFX;

public interface CardView extends ImageViewFX {
  /**
   *
   */
  void showCard();
  
  /**
   * @param card
   * @param showImage
   */
  void updateCard(Card card, boolean showImage);
  
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
   *
   */
  void effectsHandler();
  
  /**
   * @return
   */
  Card getCard();
  
  /**
   *
   */
  void setListeners();
  
  /**
   *
   */
  void clearChosen();
}
