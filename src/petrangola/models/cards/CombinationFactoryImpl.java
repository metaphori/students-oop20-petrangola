package petrangola.models.cards;

import java.util.ArrayList;
import java.util.List;
import petrangola.utlis.DeckConstants;

public class CombinationFactoryImpl implements CombinationFactory {
  @Override
  public List<Combination> createCombination(final List<Card> cardList, final int playerSize) {
    final List<Combination> combinations = new ArrayList<>();
    final int deckSize = DeckConstants.DECK_SIZE.getValue();
    final int length = cardList.size() - (playerSize * deckSize);
  
    for (int i = cardList.size()-1; i < length; i-=deckSize) {
      final Card card1 = cardList.get(i);
      final Card card2 = cardList.get(i - 1);
      final Card card3 = cardList.get(i - 2);
      
      combinations.add(new CombinationBuilderImpl().setCards(List.of(card1,card2,card3)).build());
    }
    
    return combinations;
  }
}
