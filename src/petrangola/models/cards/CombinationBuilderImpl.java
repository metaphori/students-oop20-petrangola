package petrangola.models.cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import petrangola.utlis.DeckConstants;
import petrangola.utlis.Name;
import petrangola.utlis.Pair;

public class CombinationBuilderImpl implements CombinationBuilder {
  
  private List<Card> cards;
  
  @Override
  public CombinationBuilder setCards(List<Card> cards) {
      if (cards.size() == DeckConstants.DECK_SIZE.getValue()) {
        this.cards = cards;
      } else {
        throw new IllegalStateException();
      }
      
      return this;
  }
  
  @Override
  public Combination build() {
    return new Combination() {
      @Override
      public boolean isTris() {
        return getCards().stream().allMatch(getCards().get(0)::equals);
      }
  
      @Override
      public boolean isFlush() {
        final Stream<Card> stream = getCards().stream().sorted(Comparator.comparingInt(Card::getValue));
  
        if (!areSameSuit()) {
          return false;
        }
  
        final List<Card> list = stream.collect(Collectors.toList());
        final int max = list.get(list.size()-1).getValue();
        final int sum = list.stream().mapToInt(Card::getValue).sum();
  
        return sum == max * (max + 1) / 2;
      }
      
      private boolean areSameSuit() {
        return getCards().stream().allMatch(card -> cards.get(0).getSuit().equals(card.getSuit()));
      }
  
      @Override
      public boolean isAceLow() {
        return areSameSuit() && getCards()
                                      .stream()
                                      .map(Card::getName)
                                      .collect(Collectors.toList())
                                      .containsAll(List.of(Name.ASSO, Name.DUE, Name.TRE));
      }
      
      public List<Card> getCards() {
        return cards;
      }
  
      @Override
      public Pair<List<Card>, Integer> getBest() {
        List<Card> cards = new ArrayList<>(getCards());
        
        if (isAceLow()) {
          cards = cards.stream().map(card -> {
            if (card.getName().equals(Name.ASSO)) {
              return new AceLow(card.getName(), card.getSuit());
            }
            
            return card;
          }).collect(Collectors.toList());
        }
        
        return cards.stream()
                     .collect(Collectors.groupingBy(Card::getSuit))
                     .entrySet()
                     .stream()
                     .collect(Collectors.toMap(Map.Entry::getValue, entry -> entry.getValue().stream().mapToInt(Card::getValue).sum()))
                     .entrySet()
                     .stream()
                     .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                     .map(maxEntry -> new Pair<>(maxEntry.getKey(), maxEntry.getValue()))
                     .get();
      }
    };
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CombinationBuilderImpl)) return false;
    CombinationBuilderImpl that = (CombinationBuilderImpl) o;
    return cards.equals(that.cards);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(cards);
  }
}
