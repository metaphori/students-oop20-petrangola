package petrangola.services;

import petrangola.models.cards.Card;
import petrangola.utlis.Name;

import java.util.List;
import java.util.stream.Collectors;


public interface CombinationChecker {
  /**
   * @param cards
   * @return if the cards have the same name value
   */
  static boolean isTris(List<Card> cards) {
    for (Card card : cards) {
      if (!cards.get(0).getName().equals(card.getName())) {
        return false;
      }
    }
    
    return true;
  }
  
  /**
   * @param cards
   * @return if the cards is have the same suit and are consecutive
   */
  static boolean isFlush(List<Card> cards) {
    if (!areSameSuit(cards)) {
      return false;
    }
    
    if (isAceLow(cards)) {
      return true;
    }
    
    final List<Integer> list = cards.stream()
                                     .mapToInt(CombinationChecker::getCardIntegerValue)
                                     .boxed()
                                     .sorted()
                                     .collect(Collectors.toList());
    
    final int max = list.get(list.size() - 1);
    final int min = list.get(0);
    
    return (max - min) == 2; // Overthinking things is my thing
  }
  
  /**
   * @param cards
   * @return
   */
  static boolean areSameSuit(List<Card> cards) {
    for (Card card : cards) {
      if (!cards.get(0).getSuit().equals(card.getSuit())) {
        return false;
      }
    }
    
    return true;
  }
  
  
  /**
   * @param cards
   * @return true if the Ace card is in combination with 2 and 3, obviously it has to have the same suit
   */
  static boolean isAceLow(List<Card> cards) {
    return areSameSuit(cards) && cards.stream()
                                       .map(Card::getName)
                                       .collect(Collectors.toList())
                                       .containsAll(List.of(Name.ASSO, Name.DUE, Name.TRE));
  }
  
  static int getCardIntegerValue(Card card) {
    if (card.getName().equals(Name.FANTE)) {
      return 8;
    }
    
    if (card.getName().equals(Name.CAVALLO)) {
      return 9;
    }
    
    return card.getValue();
  }
  
  static boolean isAnyKindOfPetrangola(List<Card> cardList) {
    return isFlush(cardList) || isTris(cardList);
  }
}
