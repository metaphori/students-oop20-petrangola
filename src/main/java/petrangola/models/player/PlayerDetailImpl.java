package main.java.petrangola.models.player;

import main.java.petrangola.models.cards.Card;

import java.util.Objects;

public class PlayerDetailImpl implements PlayerDetail {
  private final static int INITIAL_LIVES = 3;
  private final int turnNumber;
  private final Player player;
  
  private int playerLives = INITIAL_LIVES;
  
  private Card highCard;
  
  public PlayerDetailImpl(final Player player, final int turnNumber) {
    this.player = player;
    this.turnNumber = turnNumber;
  }
  
  @Override
  public Card getHighCard() {
    return this.highCard;
  }
  
  @Override
  public void setHighCard(Card highCard) {
    this.highCard = highCard;
  }
  
  @Override
  public int getPlayerLives() {
    return this.playerLives;
  }
  
  @Override
  public void takeLife() {
    this.playerLives--;
  }
  
  @Override
  public int getTurnNumber() {
    return this.turnNumber;
  }
  
  @Override
  public Player getPlayer() {
    return this.player;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PlayerDetailImpl)) return false;
    PlayerDetailImpl that = (PlayerDetailImpl) o;
    return getTurnNumber() == that.getTurnNumber() && getPlayerLives() == that.getPlayerLives() && getPlayer().equals(that.getPlayer()) && getHighCard().equals(that.getHighCard());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getTurnNumber(), getPlayer(), getPlayerLives(), getHighCard());
  }
}
