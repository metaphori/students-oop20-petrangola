package main.java.petrangola.views.player;

import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;

import java.util.List;

public interface GameObjectView {
  /**
   *
   */
  void showCards();
  
  CardsView<List<CardView>> getCardsView();
}
