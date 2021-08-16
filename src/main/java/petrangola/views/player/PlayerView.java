package main.java.petrangola.views.player;


import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.imageview.ImageViewFX;

import java.util.List;

public interface PlayerView extends CardsView<List<ImageViewFX>> {
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
