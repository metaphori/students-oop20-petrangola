package main.java.petrangola.views.events;

import main.java.petrangola.models.player.Player;

public class KnockEvent implements Event {
  private Player player;
  
  public KnockEvent(Player player) {
    this.player = player;
  }
  
  public Player getPlayer() {
    return this.player;
  }
}
