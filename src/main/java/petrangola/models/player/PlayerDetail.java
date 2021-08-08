package main.java.petrangola.models.player;

import main.java.petrangola.models.cards.Card;

public interface PlayerDetail  {
  /**
   *
   * @return
   */
  Card getHighCard();
  
  /**
   *
   * @param highCard
   */
  void setHighCard(Card highCard);
  
  /**
   *
   * @return
   */
  int getPlayerLives();
  
  /**
   *
   */
  void takeLife();
  
  /**
   *
   * @return
   */
  int getTurnNumber();
  
  /**
   *
   * @return
   */
  Player getPlayer();
  
}
