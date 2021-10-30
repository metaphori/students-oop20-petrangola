package petrangola.views.cards;

import javafx.scene.Group;
import petrangola.models.cards.Cards;
import petrangola.views.player.buttons.ExchangeButton;

public interface UpdatableCombination {
  CardsView<Group> getCardsView();
  /**
   * @return
   */
  ExchangeButton getExchangeButton();
  /**
   *
   */
  default void clearChosenCards() {
    this.getExchangeButton().setData(null);
    this.getCardsView().getCardViews().forEach(CardView::clearChosen);
  }
  
  /**
   * @return
   */
  CardsExchanged getCardsExchanged();
  
  /**
   * @param cardsExchanged
   */
  void setCardsExchanged(CardsExchanged cardsExchanged);
  
  /**
   * @param cardsExchanged
   * @param exchangeButton
   */
  default void enableExchangeButton(CardsExchanged cardsExchanged, ExchangeButton exchangeButton) {
    cardsExchanged.getBoardCards().ifPresent(boardCards -> {
      cardsExchanged.getPlayerCards().ifPresent(playerCards -> {
        exchangeButton.setDisable(!cardsExchanged.areExchangeable(boardCards, playerCards));
      });
    });
  }
  
  /**
   * @param cards
   */
  default void addCards(Cards cards) {
    this.getCardsExchanged().addCards(cards);
  }
  
  /**
   * @param exchangeButton
   * @param cards
   */
  default void onUpdatedCombination(ExchangeButton exchangeButton, Cards cards) {
    this.addCards(cards);
    exchangeButton.setData(this.getCardsExchanged());
    this.enableExchangeButton(this.getCardsExchanged(), exchangeButton);
  }
}
