package main.java.petrangola.models.cards;

import java.util.List;

public interface Replaceable {
  /**
   * Pretty straightforward, the only responsability of this method si
   *
   * @param cardsToPut
   * @param cardsToReplace
   */
  void replaceCards(List<Card> cardsToPut, List<Card> cardsToReplace);
}
