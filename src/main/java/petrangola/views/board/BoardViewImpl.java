package main.java.petrangola.views.board;

import javafx.scene.Group;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class BoardViewImpl implements BoardView {
  private CardsExchanged cardsExchanged;
  private CardsView<Group> cardsView;
  
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
        this.getCardsView().setCards(cards);
        this.getCardsView().update(cards);
      }
    });
  }
  
  @Override
  public void clearChosenCards() {
    this.getCardsView().getCardViews().forEach(CardView::clearChosen);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "updatedCombination":
        this.addCards((Cards) evt.getSource());
        break;
      case "firstExchange":
        this.getCardsView().showCards();
        break;
      case "exchange":
        this.clearChosenCards();
        this.updateCards((List<Cards>) evt.getNewValue());
        break;
    }
  }
  
  @Override
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  @Override
  public CardsExchanged getCardsExchanged() {
    return this.cardsExchanged;
  }
}
