package main.java.petrangola.models.player;

import java.util.List;
import main.java.petrangola.models.cards.Cards;

public interface Exchangeable {
  
  /**
   * Exchanges board and player cards by taking k card from each deck
   * @param boardCards
   * @param playerCards
   * @return
   */
  List<Cards> exchange(final Cards boardCards, final Cards playerCards);
}
