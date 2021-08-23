package main.java.petrangola.views.cards;


import javafx.scene.Group;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public interface CardsViewFactory {
  /**
   * @return
   */
  CardsView<Group> createUserCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
  /**
   * @return
   */
  CardsView<Group> createOpponentCards(final Cards cards, final int npcIndex, final int thresholdNpc);
  
  /**
   * @return
   */
  CardsView<Group> createBoardCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
  /**
   * @return
   */
  CardsView<Group> createDealerCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
}
