package main.java.petrangola.views.board;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.buttons.ExchangeButton;

import java.beans.PropertyChangeEvent;
import java.util.List;

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
    final HBox hBox = new HBox();
    final Pane centralPane = (Pane) layout.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
    final Pos pos = layoutBuilder.getPos(this.getCardsView().getPosition());
    
    hBox.setAlignment(pos);
    hBox.setSpacing(8);
    hBox.getChildren().addAll(this.getCardsView().get().getChildren());
    
    centralPane.getChildren().add(hBox);
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
  public void setExchangeButton(ExchangeButton exchangeButton) {
    this.exchangeButton = exchangeButton;
  }
}
