package main.java.petrangola.views.events;

import main.java.petrangola.models.player.Player;

import java.util.Objects;

public class KnockEvent implements Event {
  private final Player player;
  
  public KnockEvent(Player player) {
    this.player = player;
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof KnockEvent)) return false;
    KnockEvent that = (KnockEvent) o;
    return getPlayer().equals(that.getPlayer());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPlayer());
  }
}
