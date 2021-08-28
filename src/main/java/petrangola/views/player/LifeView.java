package main.java.petrangola.views.player;

import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.text.TextViewFX;

public interface LifeView extends TextViewFX {
  /**
   *
   * @return
   */
  Player getPlayer();
}
