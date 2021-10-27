package main.java.petrangola.views.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;

import java.util.Objects;

public class CurrentPlayerImpl implements CurrentPlayer {
  private Player player;
  private Cards cards;
  
  public CurrentPlayerImpl() {}
  
  @Override
  public Player getPlayer() {
    return this.player;
  }
  
  @Override
  public void setPlayer(Player player) {
    this.player = player;
  }
  
  @Override
  public Cards getCards() {
    return this.cards;
  }
  
  @Override
  public void setCards(Cards cards) {
    this.cards = cards;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CurrentPlayerImpl)) return false;
    CurrentPlayerImpl that = (CurrentPlayerImpl) o;
    return Objects.equals(getPlayer(), that.getPlayer()) && Objects.equals(getCards(), that.getCards());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPlayer(), getCards());
  }
}
