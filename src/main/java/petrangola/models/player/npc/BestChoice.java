package main.java.petrangola.models.player.npc;

import java.util.*;
import java.util.stream.Collectors;
import main.java.petrangola.models.cards.*;

public class BestChoice extends AbstractChoiceStrategy {
  private final CombinationBuilder combinationBuilder = new CombinationBuilderImpl();
  
  @Override
  public List<Cards> chooseCards(List<Cards> cardsList) {
    final List<Card> cardList = cardsList.stream()
                                      .map(Cards::getCombination)
                                      .map(Combination::getCards)
                                      .flatMap(List::stream)
                                      .collect(Collectors.toList());
    
    final Cards boardCards = getBoardCards(cardsList);
    final Cards playerCards = getPlayerCards(cardsList);
    
    final Combination maxCombination = getMaxCombination(cardList);
    final Combination complement = combinationBuilder.setCards(cardList
                                                                     .stream()
                                                                     .filter(card -> maxCombination.getCards().equals(card))
                                                                     .collect(Collectors.toList())).build();
    
    playerCards.setCombination(maxCombination);
    boardCards.setCombination(complement);
    
    return List.of(boardCards, playerCards);
  }
  
  private Combination getMaxCombination(List<Card> cardList) {
    return combinationBuilder.setCards(cardList.stream()
                                             .collect(Collectors.groupingBy(Card::getSuit))
                                             .values()
                                             .stream()
                                             .max(Comparator.comparingInt(list -> list.stream().mapToInt(Card::getValue).sum()))
                                             .get()).build();
  }
}
