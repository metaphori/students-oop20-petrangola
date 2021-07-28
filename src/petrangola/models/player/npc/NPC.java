package petrangola.models.player.npc;

import petrangola.models.player.Player;
import petrangola.utlis.DifficultyLevel;

public interface NPC extends Player {
  /**
   *
   * @return
   */
  DifficultyLevel getDifficultyLevel();
}
