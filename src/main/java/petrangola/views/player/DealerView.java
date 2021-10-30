package petrangola.views.player;

import javafx.scene.layout.Pane;
import petrangola.controllers.game.GameController;
import petrangola.models.cards.Cards;
import petrangola.views.cards.UpdatableCombination;
import petrangola.views.components.button.AbstractButtonFX;

public interface DealerView extends PlayerView, UpdatableCombination {
  /**
   *
   * @param boardCards
   */
  void init(final Cards boardCards);
  
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
  
  /*+
   *
   */
  void setGameController(GameController gameController);
  
  /**
   *
   * @param layout
   */
  void hideView(Pane layout);
  
  /**
   *
   * @param layout
   */
  void showView(Pane layout);
}
