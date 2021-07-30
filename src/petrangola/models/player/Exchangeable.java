package petrangola.models.player;

import petrangola.models.Cards;

public interface Exchangeable {
  
  /**
   * Exchanges board and player cards by taking k card from each deck
   * @param boardCards
   * @param playerCards
   */
  void exchange(final Cards boardCards, final Cards playerCards);
}
