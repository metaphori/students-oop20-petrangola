package main.java.petrangola.views.player;


import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public interface PlayerView extends GameObjectView {
  /**
   *
   */
  void showAction();
  
  /**
   * @return
   */
  AbstractButtonFX getExchangeButton();
  
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
}
