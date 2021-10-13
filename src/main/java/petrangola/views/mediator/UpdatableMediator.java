package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;

public interface UpdatableMediator extends Mediator {
  /**
   * @param propertyName
   * @param newValue
   */
  void update(String propertyName, Object newValue);
}
