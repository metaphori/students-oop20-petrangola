package main.java.petrangola.views.player;


import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public interface PlayerView extends GameObjectView {
  /**
   * @return
   */
  AbstractButtonFX getKnockButton();
  
  /**
   * @return
   */
  Player getPlayer();
  
  /**
   *
   */
  void toggleUserButton(Player player);
  
  /**
   *
   * @return
   */
  PlayerController getPlayerController();
}
