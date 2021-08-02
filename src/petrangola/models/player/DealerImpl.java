package petrangola.models.player;

import petrangola.models.cards.Card;
import petrangola.models.cards.Cards;

import java.util.List;

public class DealerImpl implements Dealer {
  final Player player;
  
  public DealerImpl(Player player) {
    this.player = player;
  }
  
  @Override
  public List<Cards> dealCards(List<Player> players) {
    return null;
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
  
  private Player getPlayer() {
    return this.player;
  }
}
