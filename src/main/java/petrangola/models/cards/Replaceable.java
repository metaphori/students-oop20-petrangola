package main.java.petrangola.models.cards;

import java.util.List;

public interface Replaceable {
  /**
   * The only responsibility of this method is to replace
   *
   * @param cardsToPut
   * @param cardsToReplace
   */
  void replaceCards(List<Card> cardsToPut, List<Card> cardsToReplace);
}
