package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;

public interface UpdatableCombination {
  void setCardsExchanged(CardsExchanged cardsExchanged);
  
  CardsExchanged getCardsExchanged();
  
  default void addCards(Cards cards) {
    getCardsExchanged().addCards(cards);
  }
}
