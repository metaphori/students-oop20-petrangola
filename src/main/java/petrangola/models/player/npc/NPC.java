package main.java.petrangola.models.player.npc;

import main.java.petrangola.models.player.Player;
import main.java.petrangola.utlis.DifficultyLevel;

public interface NPC extends Player {
  /**
   *
   * @return
   */
  DifficultyLevel getDifficultyLevel();
}
