package petrangola.models.player.npc;

import java.util.*;
import java.util.stream.Collectors;
import petrangola.models.Card;
import petrangola.models.Cards;
import petrangola.models.Combination;
import petrangola.models.CombinationImpl;

public class BestChoice extends AbstractChoiceStrategy {
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
    final Combination complement = new CombinationImpl(cardList
                                                             .stream()
                                                             .filter(card -> maxCombination.getCards().equals(card))
                                                             .collect(Collectors.toList()));
    
    playerCards.setCombination(maxCombination);
    boardCards.setCombination(complement);
    
    return List.of(boardCards, playerCards);
  }
  
  /*private List<Card> listOfAllCard(Cards... cardsModels) {
    List<Card> cardList = new ArrayList<>();
    
    for (Cards cardsModel : cardsModels) {
      cardList.addAll(cardsModel.getCombination().getCards());
    }
    
    return cardList;
  }*/
  
  private Combination getMaxCombination(List<Card> cardList) {
    return new CombinationImpl(cardList
                                     .stream()
                                     .collect(Collectors.groupingBy(Card::getSuit))
                                     .values()
                                     .stream()
                                     .max(Comparator.comparingInt(list -> list.stream().mapToInt(Card::getValue).sum()))
                                     .get());
  }
  
  /*private List<List<Card>> partitioningList(List<Card> cardList, int partitionSize) {
    return new ArrayList<>(
          IntStream.range(0, cardList.size())
                   .boxed()
                   .collect(
                         Collectors.groupingBy(index-> index / partitionSize, Collectors.mapping(cardList::get, Collectors.toList()))
                   ).values()
    );
  }*/
}
