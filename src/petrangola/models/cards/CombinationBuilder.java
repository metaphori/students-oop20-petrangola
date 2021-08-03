package petrangola.models.cards;

import java.util.List;

public interface CombinationBuilder {
  /**
   *
   * @param cards
   * @return
   */
  CombinationBuilder setCards(List<Card> cards);
  
  /**
   *
   * @return
   */
  Combination build();
}
