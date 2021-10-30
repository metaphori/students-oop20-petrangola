package petrangola.models.cards;

import petrangola.utlis.DeckConstants;

import java.util.ArrayList;
import java.util.List;

public class CombinationFactoryImpl implements CombinationFactory {
  @Override
  public List<Combination> createCombinations(final List<Card> cardList, final int playersSize) {
    
    final List<Combination> combinations = new ArrayList<>();
    final int deckSize = DeckConstants.DECK_SIZE.getValue();
    final int length = cardList.size() - ((playersSize + 1) * deckSize);
    
    
    for (int i = cardList.size() - 1; i > length; i -= deckSize) {
      final Card card1 = cardList.get(i);
      final Card card2 = cardList.get(i - 1);
      final Card card3 = cardList.get(i - 2);
      
      combinations.add(new CombinationBuilderImpl().setCards(List.of(card1, card2, card3)).build());
    }
    
    combinations.forEach(Combination::addPropertyChangeListener);
    
    return combinations;
  }
}
