package petrangola.models.game;

import java.util.List;
import petrangola.models.player.PlayerDetail;

public interface GameFactory {
  
  /**
   *
   * @return
   */
  List<PlayerDetail> createPlayerDetails();
  
  /**
   *
   * @return
   */
  List<GameObject> createGameObject();
  
}
