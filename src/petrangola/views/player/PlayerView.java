package petrangola.views.player;

import petrangola.views.GameObjectView;

public interface PlayerView extends GameObjectView {
  /**
   *
   */
  void showName();
  
  /**
   *
   * @param lifeView
   */
  void showLives(LifeView lifeView);
  
}
