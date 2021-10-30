package petrangola.models.player;

import petrangola.models.ObservableModel;
import petrangola.models.cards.Card;

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
  
  /**
   *
   * @return
   */
  boolean isStillAlive();
}
