package petrangola.models.player;

import petrangola.models.cards.Card;

public interface PlayerDetail  {
  
  Card getHighCard();
  
  int getPlayerLives();
  
  void takeLife();
  
  int getTurnNumber();
  
  Player getPlayer();
  
}
