package main.java.petrangola.models.player;

import main.java.petrangola.models.cards.Cards;

public interface Exchangeable {
  /**
   *
   * @param boardCards
   * @param playerCards
   */
  void firstExchange(final Cards boardCards, final Cards playerCards);
  
  /**
   * Exchanges board and player cards by taking k card from each deck
   * @param boardCards
   * @param playerCards
   */
  void exchange(final Cards boardCards, final Cards playerCards);
}
