package main.java.petrangola.views.cards;


import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

import java.util.List;

public interface CardsViewFactory {
  /**
   * @return
   */
  CardsView<List<CardView>> createUserCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
  /**
   * @return
   */
  CardsView<List<CardView>> createOpponentCards(final Cards cards, final int npcIndex, final int thresholdNpc);
  
  /**
   * @return
   */
  CardsView<List<CardView>> createBoardCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
  /**
   * @return
   */
  CardsView<List<CardView>> createDealerCards(final Cards cards, final Pair<Vertical, Horizontal> position);
  
}
