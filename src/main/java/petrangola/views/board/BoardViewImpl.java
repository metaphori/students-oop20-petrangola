package main.java.petrangola.views.board;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.ViewFX;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.buttons.ExchangeButton;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;

public class BoardViewImpl implements BoardView {
  private CardsExchanged cardsExchanged;
  private CardsView<Group> cardsView;
  private ExchangeButton exchangeButton;
  
  public BoardViewImpl() {
  }
  
  @Override
  public void showCards() {
    this.cardsView.getCardViews().forEach(CardView::showCard);
  }
  
  public CardsView<Group> getCardsView() {
    return this.cardsView;
  }
  
  @Override
  public void setCardsView(CardsView<Group> cardsView) {
    this.cardsView = cardsView;
  }
  
  @Override
  public void updateCards(List<Cards> cardsList) {
    cardsList.forEach(cards -> {
      if (cards.isCommunity()) {
        this.removeListenerModel(getCardsView().getCards());
        this.getCardsView().setCards(cards);
        this.getCardsView().update(cards);
        this.addListenerToModel(cards);
      }
    });
  }
  
  @Override
  public void register(Pane layout, LayoutBuilder layoutBuilder) {
    final HBox centraHBox = new HBox();
    final Pane centralPane = (Pane) layout.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
    final Pos pos = layoutBuilder.getPos(this.getCardsView().getPosition());
    
    centraHBox.setAlignment(pos);
    centraHBox.setSpacing(8);
  
    ViewFX.addOrUpdate(centralPane, centraHBox);
    ViewFX.addOrUpdateAll(centraHBox, this.getCardsView().get().getChildren());
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "updatedCombination":
        this.onUpdatedCombination(this.getExchangeButton(), (Cards) evt.getSource());
        break;
      case "firstExchange":
        this.getCardsView().showCards();
        this.updateCards((List<Cards>) evt.getNewValue());
        break;
      case "exchange":
        this.clearChosenCards();
        this.updateCards((List<Cards>) evt.getNewValue());
        break;
    }
  }
  
  @Override
  public CardsExchanged getCardsExchanged() {
    return this.cardsExchanged;
  }
  
  @Override
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  @Override
  public ExchangeButton getExchangeButton() {
    return this.exchangeButton;
  }
  
  @Override
  public boolean isBoardView() {
    return true;
  }
  
  @Override
  public boolean isDealer() {
    return false;
  }
  
  @Override
  public boolean isUser() {
    return false;
  }
  
  @Override
  public void setExchangeButton(ExchangeButton exchangeButton) {
    this.exchangeButton = exchangeButton;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BoardViewImpl)) return false;
    BoardViewImpl boardView = (BoardViewImpl) o;
    return Objects.equals(getCardsExchanged(), boardView.getCardsExchanged()) && getCardsView().equals(boardView.getCardsView()) && Objects.equals(getExchangeButton(), boardView.getExchangeButton());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getCardsExchanged(), getCardsView(), getExchangeButton());
  }
}
