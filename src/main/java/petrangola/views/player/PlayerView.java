package main.java.petrangola.views.player;


import main.java.petrangola.views.components.button.AbstractButtonFX;

public interface PlayerView extends GameObjectView {
  /**
   *
   */
  void showName();
  
  /**
   *
   */
  void showLives();
  
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
  
}
