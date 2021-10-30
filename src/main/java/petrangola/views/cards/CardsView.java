package petrangola.views.cards;

import petrangola.models.cards.Cards;
import petrangola.views.components.ViewNode;

import java.util.List;

public interface CardsView<T> extends ViewNode<T> {
  /**
   *
   */
  void showCards();
  
  /**
   * @return
   */
  List<CardView> getCardViews();
  
  /**
   *
   */
  Cards getCards();
  
  /**
   * @param cards
   */
  void setCards(Cards cards);
  
  /**
   *
   * @param cards
 
   */
  void update(Cards cards);
}
