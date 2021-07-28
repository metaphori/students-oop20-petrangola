package petrangola.models;

import petrangola.models.player.Player;

import java.util.List;
import java.util.Optional;

public interface Cards {
  
  List<Card> getCombination();
  
  void setCombination(List<Card> cards);
  
  boolean isCommunity();
  
  boolean isPlayerCards();
  
  Optional<Player> getPlayer();
  
}
