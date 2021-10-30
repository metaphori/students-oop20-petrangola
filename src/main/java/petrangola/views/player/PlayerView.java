package petrangola.views.player;


import petrangola.controllers.player.PlayerController;
import petrangola.models.player.Player;

public interface PlayerView extends GameObjectView {
  /**
   * @return
   */
  Player getPlayer();
  
  /**
   *
   * @return
   */
  PlayerController getPlayerController();
  
  /**
   *
   * @param playerLives
   */
  void updateLifeView(int playerLives);
}
