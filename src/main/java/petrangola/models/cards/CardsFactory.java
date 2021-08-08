package main.java.petrangola.models.cards;

import java.util.List;
import java.util.Map;
import main.java.petrangola.models.game.GameObject;

public interface CardsFactory {
  /**
   *
   * @param map
   * @return
   */
  List<Cards> createCards(final Map<GameObject, Combination> map);
  
}
