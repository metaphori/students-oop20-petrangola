package main.java.petrangola.models.player.npc;

import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.events.KnockEvent;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Random;

public class RandomChoice extends AbstractChoiceStrategy {
  @Override
  public List<Cards> chooseCards(List<Cards> cardsList) {
    final Random random = new Random();
    final int indexToTake = random.nextInt(2);
    final int indexToGive = random.nextInt(2);
    
    final Cards boardCards = getBoardCards(cardsList);
    final Cards playerCards = getPlayerCards(cardsList);
    final Card playerCard = playerCards.getCombination().getCards().get(indexToGive);
    final Card boardCard = boardCards.getCombination().getCards().get(indexToTake);
    
    if (Math.random() < 0.5) {
      playerCards.getCombination().replaceCards(List.of(boardCard), List.of(playerCard));
      boardCards.getCombination().replaceCards(List.of(playerCard), List.of(boardCard));
    } else {
      EventBus.getDefault().post(new KnockEvent(playerCards.getPlayer().get()));
    }
    
    return List.of(boardCards, playerCards);
  }
}
