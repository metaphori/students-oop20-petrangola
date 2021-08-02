package petrangola.models.player;

import petrangola.models.player.npc.NPC;
import petrangola.utlis.DifficultyLevel;

import java.util.List;

public interface PlayerFactory {
  
  User createUser(final String username);
  
  List<NPC> createNPC(final int size, final DifficultyLevel difficultyLevel);
  
}
