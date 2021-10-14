package main.java.petrangola.views.player;


import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.player.buttons.ExchangeButton;

public interface PlayerView extends GameObjectView {
  /**
   *
   */
  void showAction();
  
  /**
   * @return
   */
  AbstractButtonFX getExchangeButton();
  
  /**
   * @return
   */
  AbstractButtonFX getKnockButton();
  
  /**
   * @return
   */
  Player getPlayer();
  
  /**
   *
   */
  void toggleUserButton(Player player);
  
  /**
   *
   * @param cardsExchanged
   * @param exchangeButton
   */
  void enableExchangeButton(CardsExchanged cardsExchanged, ExchangeButton exchangeButton);
}
