package petrangola.models.cards;

import petrangola.models.board.Board;
import petrangola.models.game.GameObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardsFactoryImpl implements CardsFactory {
  @Override
  public List<Cards> createCards(final Map<GameObject, Combination> map) {
    
    return map.entrySet()
                 .stream()
                 .map(entry -> new CardsImpl(entry.getValue(), entry.getKey()))
                 .collect(Collectors.toList());
  }
}
