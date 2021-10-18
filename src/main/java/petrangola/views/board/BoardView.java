package main.java.petrangola.views.board;

import main.java.petrangola.views.cards.UpdatableCombination;
import main.java.petrangola.views.player.GameObjectView;
import main.java.petrangola.views.player.buttons.ExchangeButton;

public interface BoardView extends GameObjectView, UpdatableCombination {
  /**
   *
   * @param exchangeButton
   */
  void setExchangeButton(ExchangeButton exchangeButton);
  
  /**
   *
   * @return
   */
  ExchangeButton getExchangeButton();
}
