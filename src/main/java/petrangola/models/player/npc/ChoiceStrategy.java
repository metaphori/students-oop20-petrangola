package petrangola.models.player.npc;

import petrangola.models.cards.Cards;

import java.util.List;

public interface ChoiceStrategy {
  /**
   * @param cardsList that has inside a player cards model and the board cards model
   * @return the two model updated after applying the choosing strategy
   */
  List<Cards> chooseCards(List<Cards> cardsList);
}
