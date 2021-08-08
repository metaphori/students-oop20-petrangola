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
  
    // TODO: check, I'm not sure, it's probably a concurrent modification
    playerCards.getCombination().getCards().set(indexToTake, toGive);
    boardCards.getCombination().getCards().set(indexToGive, toTake);
    
    return List.of(boardCards, playerCards);
  }
}
