package main.java.petrangola.views.board;

import javafx.scene.Group;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;

public class BoardViewImpl implements BoardView {
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
}
