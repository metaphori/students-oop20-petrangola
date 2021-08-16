package main.java.petrangola.models.player;

import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.*;
import main.java.petrangola.models.game.GameObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DealerImpl implements Dealer {
  final Player player;
  
  public DealerImpl(Player player) {
    this.player = player;
  }
  
  @Override
  public List<Cards> dealCards(List<PlayerDetail> playerDetails, Board board) {
    final CardFactory cardFactory = new CardFactoryImpl();
    final CardsFactory cardsFactory = new CardsFactoryImpl();
    final CombinationFactory combinationFactory = new CombinationFactoryImpl();
    final Map<GameObject, Combination> cardsToDeal = new HashMap<>();
    final List<Combination> combinations = combinationFactory.createCombinations(cardFactory.createDeck(), playerDetails.size());
    
    System.out.println(combinations);
    for (int index = 0; index < playerDetails.size() - 1; index++) {
      cardsToDeal.put(playerDetails.get(index).getPlayer(), combinations.get(index));
    }
    
    cardsToDeal.put(board, combinations.get(combinations.size() - 1));
    
    return cardsFactory.createCards(cardsToDeal);
  }
  
  @Override
  public List<Cards> firstExchange(Cards boardCards, Cards playerCards) {
    return getPlayer().firstExchange(boardCards, playerCards);
  }
  
  @Override
  public List<Cards> exchange(Cards boardCards, Cards playerCards) {
    return getPlayer().exchange(boardCards, playerCards);
  }
  
  @Override
  public String getUsername() {
    return getPlayer().getUsername();
  }
  
  @Override
  public boolean isNPC() {
    return getPlayer().isNPC();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DealerImpl)) return false;
    DealerImpl dealer = (DealerImpl) o;
    return getPlayer().equals(dealer.getPlayer());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPlayer());
  }
  
  private Player getPlayer() {
    return this.player;
  }
}
