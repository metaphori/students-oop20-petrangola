package petrangola.models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import petrangola.utlis.DeckConstants;

public class CombinationImpl implements Combination {
  List<Card> cards;
  
  public CombinationImpl(final List<Card> cards) {
    this.cards = cards;
  }
  
  @Override
  public boolean isTris() {
    return getCards().stream().allMatch(getCards().get(0)::equals);
  }
  
  @Override
  public boolean isFlush() {
    final Stream<Card> stream = getCards().stream().sorted(Comparator.comparingInt(Card::getValue));
    
    if (stream.noneMatch(card -> card.getSuit().equals(getCards().get(0).getSuit()))) {
      return false;
    }
    
    final List<Card> list = stream.collect(Collectors.toList());
    final int max = list.get(list.size()-1).getValue();
    final int sum = list.stream().mapToInt(Card::getValue).sum();
    
    return sum == max * (max + 1) / 2;
  }
  
  @Override
  public List<Card> getBest() {
    // return get best probably is better as an int
    
    return null;
  }
  
  @Override
  public List<Card> getCards() {
    return this.cards;
  }
  
  /*@Override
  public void setCards(List<Card> cards) {
    if (cards.size() > DeckConstants.DECK_SIZE.getValue()) {
      throw new IllegalStateException();
    }
  
    this.cards = cards;
  }*/
}
