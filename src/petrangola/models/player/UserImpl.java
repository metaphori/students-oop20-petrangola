package petrangola.models.player;

import petrangola.models.Cards;

import java.util.List;

public class UserImpl implements User {
  private final String username;
  
  public UserImpl(final String username) {
    this.username = username;
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
}
