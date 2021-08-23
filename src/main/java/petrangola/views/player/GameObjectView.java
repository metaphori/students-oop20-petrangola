package main.java.petrangola.views.player;

import javafx.scene.Group;
import main.java.petrangola.views.cards.CardsView;

public interface GameObjectView {
  /**
   *
   */
  void showCards();
  
  CardsView<Group> getCardsView();
}
