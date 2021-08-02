package petrangola.models.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import petrangola.utlis.Name;
import petrangola.utlis.Suit;

public class CardFactoryImpl implements CardFactory {
  @Override
  public List<Card> createDeck() {
    return shuffleCards(createSimpleDeck());
  }
  
  private List<Card> shuffleCards(List<Card> deck) {
    final Random random = new Random();
    final List<Card> tempDeck = new ArrayList<>(deck);
  
    IntStream.rangeClosed(tempDeck.size() - 1,0 )
          .boxed()
          .forEachOrdered(index -> {
            final int randomIndex = (int) Math.floor(random.nextInt()*index);
            final Card temp = tempDeck.get(randomIndex);
            
            tempDeck.set(index, tempDeck.get(randomIndex));
            tempDeck.set(randomIndex, temp);
          });
    
    return tempDeck;
  }
  
  private List<Card> createSimpleDeck() {
    return Arrays.stream(Suit.values())
                 .flatMap(suit -> Arrays.stream(Name.values()).map(name -> new CardImpl(name, suit)))
                 .collect(Collectors.toList());
  }
}
