package main.java.petrangola.models.player;

import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;

import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Objects;

public class DealerImpl implements Dealer {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  
  private final Player player;
  
  public DealerImpl(Player player) {
    this.player = player;
  }
  
  @Override
  public void dealCards(List<PlayerDetail> playersDetails, Board board) {
    firePropertyChange("dealtCards", null, getCardsToDeal(playersDetails, board));
  }
  
  @Override
  public void firstExchange(Cards boardCards, Cards playerCards) {
    getPlayer().firstExchange(boardCards, playerCards);
  }
  
  @Override
  public void exchange(Cards boardCards, Cards playerCards) {
    getPlayer().exchange(boardCards, playerCards);
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
  public boolean isDealer() {
    return getPlayer().isDealer();
  }
  
  @Override
  public void setIsDealer(boolean isDealer) {
    getPlayer().setIsDealer(isDealer);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DealerImpl)) return false;
    DealerImpl dealer = (DealerImpl) o;
    return getSupport().equals(dealer.getSupport()) && getPlayer().equals(dealer.getPlayer());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getSupport(), getPlayer());
  }
  
  private Player getPlayer() {
    return this.player;
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
