package main.java.petrangola.views.board;

import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;

import java.util.List;

public class BoardViewImpl implements BoardView {
  private final CardsView<List<CardView>> cardsView;
  
  public BoardViewImpl(final CardsView<List<CardView>> cardsView) {
    this.cardsView = cardsView;
  }
  
  @Override
  public void showCards() {
    this.cardsView.getCardsViews().forEach(CardView::showCard);
  }
  
  public CardsView<List<CardView>> getCardsView() {
    return this.cardsView;
  }
}
