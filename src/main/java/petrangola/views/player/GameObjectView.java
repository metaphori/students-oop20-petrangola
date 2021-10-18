package main.java.petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.View;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;

import java.util.List;

public interface GameObjectView extends View {
  /**
   *
   */
  void showCards();
  
  /**
   *
   * @return
   */
  CardsView<Group> getCardsView();
  
  /**
   *
   * @param cardsView
   */
  void setCardsView(CardsView<Group> cardsView);
  
  /**
   *
   * @param cardsList
   */
  void updateCards(List<Cards> cardsList);
  
  /**
   *
   * @param layout
   * @param layoutBuilder
   */
  void register(Pane layout, LayoutBuilder layoutBuilder);
  
  /**
   * @return
   */
  AbstractButtonFX getExchangeButton();
  
  /**
   *
   */
  default void clearChosenCards() {
    this.getExchangeButton().setData(null);
    this.getCardsView().getCardViews().forEach(CardView::clearChosen);
  }
}
