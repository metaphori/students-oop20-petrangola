package main.java.petrangola.views.player;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.cards.UpdatableCombination;
import main.java.petrangola.views.components.button.AbstractButtonFX;

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
