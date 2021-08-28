package main.java.petrangola.models.player.npc;

import java.util.List;
import java.util.Random;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.Cards;

public class RandomChoice extends AbstractChoiceStrategy {
  @Override
  public List<Cards> chooseCards(List<Cards> cardsList) {
    final Random random = new Random();
    final int indexToTake = random.nextInt(2);
    final int indexToGive = random.nextInt(2);
    
    final Cards boardCards = getBoardCards(cardsList);
    final Cards playerCards = getPlayerCards(cardsList);
    final Card toGive = playerCards.getCombination().getCards().get(indexToGive);
    final Card toTake = boardCards.getCombination().getCards().get(indexToTake);
  
    playerCards.getCombination().replaceCards(List.of(toGive), List.of(toTake));
    boardCards.getCombination().replaceCards(List.of(toTake), List.of(toGive));
    
    return List.of(boardCards, playerCards);
  }
}
