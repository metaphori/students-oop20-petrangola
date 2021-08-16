package main.java.petrangola.views.cards;

import main.java.petrangola.views.components.hierarchy.Parent;
import main.java.petrangola.views.components.ViewNode;

public interface CardsView<P> extends ViewNode<P>, Parent<P> {
  /**
   *
   */
  void showCards();
  
  /**
   *
   */
  void highlightBestCardsCombination();
}
