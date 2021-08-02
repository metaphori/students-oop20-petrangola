package petrangola.models.player;

import petrangola.models.player.npc.NPC;
import petrangola.models.player.npc.NPCImpl;
import petrangola.utlis.DifficultyLevel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerFactoryImpl implements PlayerFactory {
  @Override
  public User createUser(final String username) {
    return new UserImpl(username);
  }
  
  @Override
  public List<NPC> createNPC(final int size, final DifficultyLevel difficultyLevel) {
    return IntStream.range(0, size)
                 .boxed()
                 .map(id -> new NPCImpl(id, difficultyLevel))
                 .collect(Collectors.toList());
  }
}
