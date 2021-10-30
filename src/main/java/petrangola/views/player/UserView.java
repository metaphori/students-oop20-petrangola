package petrangola.views.player;

import petrangola.views.cards.UpdatableCombination;
import petrangola.views.components.button.AbstractButtonFX;

public interface UserView extends PlayerView, UpdatableCombination {
  /**
   * @return
   */
  AbstractButtonFX getKnockButton();
}
