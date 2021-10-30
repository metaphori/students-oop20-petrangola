package petrangola.models.cards;

import petrangola.utlis.Name;
import petrangola.utlis.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactoryImpl implements CardFactory {
  @Override
  public List<Card> createDeck() {
    return shuffleCards(createSimpleDeck());
  }
  
  private List<Card> shuffleCards(List<Card> deck) {
    final List<Card> tempDeck = new ArrayList<>(deck);
    
    Collections.shuffle(tempDeck);
    
    return tempDeck;
  }
  
  private List<Card> createSimpleDeck() {
    return Arrays.stream(Suit.values())
                 .flatMap(suit -> Arrays.stream(Name.values()).map(name -> new CardImpl(name, suit)))
                 .collect(Collectors.toList());
  }
}
