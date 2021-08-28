package main.java.petrangola.views.player;


import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public interface PlayerView extends GameObjectView {
  /**
   *
   */
  void showName();
  
  /**
   *
   */
  void hideName();
  
  /**
   *
   */
  void showLives();
  
  /**
   *
   */
  void hideLives();
  
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
  LifeView getLifeView();
  
  /**
   *
   * @return
   */
  UsernameView getUsernameView();
  
  /**
   *
   * @return
   */
  Player getPlayer();
}
