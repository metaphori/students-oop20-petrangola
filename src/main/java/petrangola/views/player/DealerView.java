package main.java.petrangola.views.player;

import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.mediator.HighCardMediator;

public interface DealerView extends PlayerView {
  /**
   *
   * @param gameController
   * @param dealerController
   * @param boardCards
   */
  void init(final GameController gameController, final DealerController dealerController, final Cards boardCards, final HighCardMediator highCardMediator);
  
  /**
   *
   * @return
   */
  AbstractButtonFX getAcceptDealtCardsButton();
  
  /**
   *
   * @return
   */
  AbstractButtonFX getFirstExchangeButton();
}
