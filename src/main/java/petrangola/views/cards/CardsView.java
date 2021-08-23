package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.components.ViewNode;


public interface CardsView<T> extends ViewNode<T> {
  /**
   *
   */
  void showCards();
  
  /**
   * @return
   */
  T getCardsViews();
  
  /**
   *
   */
  Cards getCards();
  
  /**
   *
   */
  void highlightBestCardsCombination();
}
