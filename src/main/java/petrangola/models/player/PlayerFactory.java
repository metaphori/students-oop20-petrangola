package main.java.petrangola.models.player;

import main.java.petrangola.models.player.npc.NPC;
import main.java.petrangola.utlis.DifficultyLevel;

import java.util.List;

public interface PlayerFactory {
  
  User createUser(final String username);
  
  List<NPC> createNPC(final int size, final DifficultyLevel difficultyLevel);
  
}
