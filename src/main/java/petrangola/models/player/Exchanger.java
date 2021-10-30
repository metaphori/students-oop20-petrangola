package petrangola.models.player;

import petrangola.models.cards.Cards;

public interface Exchanger {
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
