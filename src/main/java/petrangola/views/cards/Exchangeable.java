package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;

public interface Exchangeable {
  /**
   *
   * @param boardCards
   * @param playerCards
   * @return
   */
  default boolean areExchangeable(final Cards boardCards, final Cards playerCards) {
    return boardCards.getCombination().getChosenCards().size() == playerCards.getCombination().getChosenCards().size();
  }
}
