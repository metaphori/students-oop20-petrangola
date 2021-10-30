package petrangola.views.board;

import petrangola.views.cards.UpdatableCombination;
import petrangola.views.player.GameObjectView;
import petrangola.views.player.buttons.ExchangeButton;

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
