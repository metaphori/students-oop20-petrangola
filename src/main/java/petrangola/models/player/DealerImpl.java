package main.java.petrangola.models.player;

import java.util.List;
import java.util.Objects;
import main.java.petrangola.models.cards.Cards;

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
}
