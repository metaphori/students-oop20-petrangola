package petrangola.models;

import java.util.Optional;
import petrangola.models.player.Player;

public interface Cards {
  
  Combination getCombination();
  
  void setCombination(Combination combination);
  
  boolean isCommunity();
  
  boolean isPlayerCards();
  
  Optional<Player> getPlayer();
  
  Optional<Board> getBoard();
}
