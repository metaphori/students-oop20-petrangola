package main.java.petrangola.views.player;

import javafx.scene.Group;
import main.java.petrangola.views.View;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardsView;

public interface GameObjectView extends View {
  void showCards();
  
  CardsView<Group> getCardsView();
  
  void setCardsView(CardsView<Group> cardsView);
  
  void setBoardView(BoardView boardView);
  
}
