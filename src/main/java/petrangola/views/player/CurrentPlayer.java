package main.java.petrangola.views.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;

public interface CurrentPlayer {
  
  Player getPlayer();
  
  void setPlayer(Player player);
  
  Cards getCards();
  
  void setCards(Cards cards);
}
