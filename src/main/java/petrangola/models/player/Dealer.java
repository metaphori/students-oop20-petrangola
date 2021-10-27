package main.java.petrangola.models.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.*;
import main.java.petrangola.models.game.GameObject;

public interface Dealer extends Player {
  /**
   *
   * @param playersDetails
   * @param board
   */
  void dealCards(List<PlayerDetail> playersDetails, Board board);
  
  /**
   *
   * @param playersDetails
   * @param board
   * @return
   */
  default List<Cards> getCardsToDeal(List<PlayerDetail> playersDetails, Board board) {
    final List<PlayerDetail> playersDetailsLeft = playersDetails.stream().filter(PlayerDetail::isStillAlive).collect(Collectors.toList());
    final CardFactory cardFactory = new CardFactoryImpl();
    final CardsFactory cardsFactory = new CardsFactoryImpl();
    final CombinationFactory combinationFactory = new CombinationFactoryImpl();
    final Map<GameObject, Combination> cardsToDeal = new HashMap<>();
    final List<Combination> combinations = combinationFactory.createCombinations(cardFactory.createDeck(), playersDetailsLeft.size());
  
    for (int index = 0; index < playersDetailsLeft.size(); index++) {
      cardsToDeal.put(playersDetailsLeft.get(index).getPlayer(), combinations.get(index));
    }
  
    cardsToDeal.put(board, combinations.get(combinations.size() - 1));
  
  
    return cardsFactory.createCards(cardsToDeal);
  }
  
}
