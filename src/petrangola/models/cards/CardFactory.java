package petrangola.models.cards;

import java.util.List;

/**
 *
 */
public interface CardFactory {
  
  /**
   *
   * @return
   */
  List<Card> createDeck();
  
}
