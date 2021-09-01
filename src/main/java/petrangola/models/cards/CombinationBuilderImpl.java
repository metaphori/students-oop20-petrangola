package main.java.petrangola.models.cards;

import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.DeckConstants;
import main.java.petrangola.utlis.Name;
import main.java.petrangola.utlis.Pair;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
      final PropertyChangeSupport support = new PropertyChangeSupport(this);
  
      @Override
      public void addPropertyChangeListener() {
        cards.forEach(card -> card.addPropertyChangeListener(this));
      }
  
      @Override
      public PropertyChangeSupport getSupport() {
        return this.support;
      }
  
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        this.replaceUpdatedCard((Card) evt.getSource());
      }
      
      private void replaceUpdatedCard(Card card) {
        List<Card> cardList = new ArrayList<>(getCards());
        
        int index = cardList.indexOf(card);
  
        if (index != -1) {
          cardList.set(index, card);
          setCards(cardList);
  
          firePropertyChange("combination", null, this);
        }
      }
      
      @Override
      public void replaceCards(List<Card> cardsToPut, List<Card> cardsToReplace) {
        final List<Card> tempCards = new ArrayList<>(getCards());
        
        cardsToReplace.forEach(tempCards::remove);
        tempCards.addAll(cardsToPut);
        
        cards.forEach(card -> card.removePropertyChangeLister(this));
        
        setCards(tempCards);
        
        cards.forEach(card -> card.addPropertyChangeListener(this));
        
      }
      
      public List<Card> getCards() {
        return cards;
      }
  
      @Override
      public List<Card> getChosenCards() {
        return cards.stream()
                     .filter(Card::isChosen)
                     .collect(Collectors.toList());
      }
  
      @Override
      public Pair<List<Card>, Integer> getBest() {
        List<Card> cards = new ArrayList<>(getCards());
        
        if (CombinationChecker.isTris(cards)) {
          return new Pair<>(cards,cards.get(0).getValue() * DeckConstants.DECK_SIZE.getValue());
        }
        
        if (CombinationChecker.isAceLow(cards)) {
          cards = cards.stream().map(card -> {
            if (card.getName().equals(Name.ASSO)) {
              return new AceLow(card.getName(), card.getSuit());
            }
            
            return card;
          }).collect(Collectors.toList());
        }
        
        int bestValue = cards.stream()
                              .collect(Collectors.groupingBy(Card::getSuit))
                              .entrySet()
                              .stream()
                              .collect(Collectors.toMap(Map.Entry::getValue, entry -> entry.getValue().stream().mapToInt(Card::getValue).sum()))
                              .entrySet()
                              .stream()
                              .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                              .map(Map.Entry::getValue)
                              .get();
        
        return new Pair<>(cards, bestValue);
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
