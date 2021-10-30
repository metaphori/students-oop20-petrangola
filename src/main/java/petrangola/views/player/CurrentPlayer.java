package petrangola.views.player;

import petrangola.models.cards.Cards;
import petrangola.models.player.Player;

public interface CurrentPlayer {
  
  Player getPlayer();
  
  void setPlayer(Player player);
  
  Cards getCards();
  
  void setCards(Cards cards);
}
