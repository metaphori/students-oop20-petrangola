package petrangola.models.cards;

import java.util.List;

public interface CombinationFactory {
  /**
   *
   * @param cardList
   * @param playersSize
   * @return
   */
  List<Combination> createCombinations(final List<Card> cardList, final int playersSize);
  
}
