package petrangola.models.cards;

import java.util.List;
import java.util.Map;
import petrangola.models.game.GameObject;

public interface CardsFactory {
  
  /**
   *
   * @param map
   * @return
   */
  List<Cards> createCards(final Map<GameObject, Combination> map);
  
}
