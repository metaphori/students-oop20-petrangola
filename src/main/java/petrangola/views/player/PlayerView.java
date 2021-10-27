package main.java.petrangola.views.player;


import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.player.Player;

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
