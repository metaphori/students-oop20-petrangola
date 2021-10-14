package main.java.petrangola.views.player;

import javafx.scene.Group;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.View;
import main.java.petrangola.views.cards.CardsView;

import java.util.List;

public interface GameObjectView extends View {
  void showCards();
  
  CardsView<Group> getCardsView();
  
  void setCardsView(CardsView<Group> cardsView);
  
  void updateCards(List<Cards> cardsList);
  
  void clearChosenCards();
}
