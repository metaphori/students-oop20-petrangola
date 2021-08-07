package petrangola.models.game;

import java.util.List;

import petrangola.models.player.Dealer;
import petrangola.models.player.Player;
import petrangola.models.player.PlayerDetail;

public interface GameFactory {
  
  /**
   *
   * @return
   */
  List<PlayerDetail> createPlayerDetails(final List<Player> players);
  
  /**
   *
   * @return
   */
  //List<GameObject> createGameObject(final List<PlayerDetail> list, final Dealer dealer);
  
}
