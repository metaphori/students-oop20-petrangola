package main.java.petrangola.views.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;

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
}
