package main.java.petrangola.models.player;

import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.models.cards.Card;

public interface PlayerDetail extends ObservableModel {
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
   * @param isTaking
   */
  void lifeHandler(boolean isTaking);
  
  /**
   *
   * @return
   */
  int getTurnNumber();
  
  /**
   *
   * @param turnNumber
   */
  void setTurnNumber(int turnNumber);
  
  /**
   *
   * @return
   */
  Player getPlayer();
}
