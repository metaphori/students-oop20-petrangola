package petrangola.views.cards;


import javafx.scene.Group;
import petrangola.models.cards.Cards;
import petrangola.utlis.Pair;
import petrangola.utlis.position.Horizontal;
import petrangola.utlis.position.Vertical;

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
