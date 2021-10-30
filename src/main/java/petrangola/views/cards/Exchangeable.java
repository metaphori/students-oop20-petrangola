package petrangola.views.cards;

import petrangola.models.cards.Cards;

public interface Exchangeable {
  /**
   *
   * @param boardCards
   * @param playerCards
   * @return
   */
  default boolean areExchangeable(final Cards boardCards, final Cards playerCards) {
    final int boardCardsChosenSize = boardCards.getCombination().getChosenCards().size();
    final int playerCardsChosenSize = playerCards.getCombination().getChosenCards().size();
    
    return boardCardsChosenSize > 0 && boardCardsChosenSize == playerCardsChosenSize;
  }
}
