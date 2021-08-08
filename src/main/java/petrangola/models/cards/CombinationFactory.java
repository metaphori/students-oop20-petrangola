package main.java.petrangola.models.cards;

import java.util.List;

public interface CombinationFactory {
  /**
   *
   * @param cardList
   * @param playerSize
   * @return
   */
  List<Combination> createCombination(final List<Card> cardList, final int playerSize);
  
}
