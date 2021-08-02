package petrangola.models.player;

import petrangola.models.cards.Card;

public class PlayerDetailImpl implements PlayerDetail {
  private final static int INITIAL_LIVES = 3;
  private final Card card;
  private final int turnNumber;
  private final Player player;
  
  private int playerLives = INITIAL_LIVES;
  
  public PlayerDetailImpl(final Player player, final Card card, final int turnNumber) {
    this.player = player;
    this.card = card;
    this.turnNumber = turnNumber;
  }
  
  @Override
  public Card getHighCard() {
    return this.card;
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
}
