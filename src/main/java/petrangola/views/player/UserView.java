package main.java.petrangola.views.player;

import main.java.petrangola.views.cards.UpdatableCombination;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public interface UserView extends PlayerView, UpdatableCombination {
  /**
   * @return
   */
  AbstractButtonFX getKnockButton();
}
