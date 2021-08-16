package main.java.petrangola.models.player;

import main.java.petrangola.models.cards.Cards;

import java.util.List;
import java.util.Objects;

public class UserImpl implements User {
  private final String username;
  
  public UserImpl(final String username) {
    this.username = username;
  }
  
  @Override
  public List<Cards> firstExchange(Cards boardCards, Cards playerCards) {
    return List.of(boardCards, playerCards);
  }
  
  @Override
  public List<Cards> exchange(Cards boardCards, Cards playerCards) {
    return List.of(boardCards, playerCards);
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserImpl)) return false;
    UserImpl user = (UserImpl) o;
    return getUsername().equals(user.getUsername());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getUsername());
  }
}
