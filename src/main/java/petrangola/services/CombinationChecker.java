package main.java.petrangola.services;

import main.java.petrangola.models.cards.Card;
import main.java.petrangola.utlis.Name;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CombinationChecker {
  /**
   * @param cards
   * @return if the cards have the same name value
   */
  static boolean isTris(List<Card> cards) {
    return cards.stream().allMatch(cards.get(0)::equals);
  }
  
  /**
   * @param cards
   * @return if the cards is have the same suit and are consecutive
   */
  static boolean isFlush(List<Card> cards) {
    final Stream<Card> stream = cards.stream().sorted(Comparator.comparingInt(Card::getValue));
    
    if (!areSameSuit(cards)) {
      return false;
    }
    
    final List<Card> list = stream.collect(Collectors.toList());
    final int max = list.get(list.size() - 1).getValue();
    final int sum = list.stream().mapToInt(Card::getValue).sum();
    
    return sum == max * (max + 1) / 2;
  }
  
  /**
   * @param cards
   * @return
   */
  static boolean areSameSuit(List<Card> cards) {
    return cards.stream().allMatch(card -> cards.get(0).getSuit().equals(card.getSuit()));
  }
  
  
  /**
   * @param cards
   * @return true if the Ace card is in combination with 2 and 3, obviously it has to have the same suit
   */
  static boolean isAceLow(List<Card> cards) {
    return areSameSuit(cards) && cards
                                       .stream()
                                       .map(Card::getName)
                                       .collect(Collectors.toList())
                                       .containsAll(List.of(Name.ASSO, Name.DUE, Name.TRE));
  }
}
