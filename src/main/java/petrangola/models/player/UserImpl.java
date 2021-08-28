package main.java.petrangola.models.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.cards.CardsImpl;
import main.java.petrangola.models.game.GameObject;

import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Objects;

public class UserImpl implements User {
  private final String username;
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private boolean isDealer = false;
  
  public UserImpl(final String username) {
    this.username = username;
  }
  
  @Override
  public void firstExchange(Cards boardCards, Cards playerCards) {
    Cards tempBoardCards = new CardsImpl(playerCards.getCombination(), boardCards.getBoard().get());
    Cards tempPlayerCards = new CardsImpl(boardCards.getCombination(), playerCards.getPlayer().get());
    
    firePropertyChange("firstExchange", null, List.of(tempPlayerCards, tempBoardCards));
  }
  
  @Override
  public void exchange(Cards boardCards, Cards playerCards) {
    firePropertyChange("exchange", null, List.of(boardCards, playerCards));
  }
  
  @Override
  public String getUsername() {
    return this.username;
  }
  
  @Override
  public boolean isNPC() {
    return false;
  }
  
  @Override
  public boolean isDealer() {
    return this.isDealer;
  }
  
  @Override
  public void setIsDealer(boolean isDealer) {
    this.isDealer = isDealer;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserImpl)) return false;
    UserImpl user = (UserImpl) o;
    return getUsername().equals(user.getUsername());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getUsername(), getSupport());
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
