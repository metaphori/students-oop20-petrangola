package petrangola.models.cards;

import java.util.Optional;

import petrangola.models.board.Board;
import petrangola.models.player.Player;

public interface Cards {
  /**
   *
   * @return
   */
  Combination getCombination();
  
  /**
   *
   * @param combination
   */
  void setCombination(Combination combination);
  
  /**
   *
   * @return
   */
  boolean isCommunity();
  
  /**
   *
   * @return
   */
  boolean isPlayerCards();
  
  /**
   *
   * @return
   */
  Optional<Player> getPlayer();
  
  /**
   *
   * @return
   */
  Optional<Board> getBoard();
  
}
