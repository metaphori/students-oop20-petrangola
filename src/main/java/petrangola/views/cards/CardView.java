package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Card;
import main.java.petrangola.views.components.imageview.ImageViewFX;

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
