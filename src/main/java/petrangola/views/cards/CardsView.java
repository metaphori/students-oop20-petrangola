package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.components.ViewNode;

import java.util.List;

public interface CardsView<T> extends ViewNode<T> {
  /**
   *
   */
  void showCards();
  
  /**
   * @return
   */
  List<CardView> getCardsViews();
  
  /**
   *
   */
  Cards getCards();
  
  /**
   *
   */
  void highlightBestCardsCombination();
}
