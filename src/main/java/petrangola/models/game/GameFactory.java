package main.java.petrangola.models.game;

import java.util.List;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;

public interface GameFactory {
  
  /**
   *
   * @return
   */
  List<PlayerDetail> createPlayerDetails(final List<Player> players);
}
