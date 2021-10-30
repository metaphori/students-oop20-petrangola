package petrangola.models.player.npc;

import petrangola.models.cards.Cards;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class AbstractChoiceStrategy implements ChoiceStrategy {
  Cards getBoardCards(List<Cards> cardsList) {
    return getCardsByPredicate(cardsList, Cards::isCommunity);
  }
  
  Cards getPlayerCards(List<Cards> cardsList) {
    return getCardsByPredicate(cardsList, Cards::isPlayerCards);
  }
  
  private Cards getCardsByPredicate(List<Cards> cardsList, Predicate<? super Cards> predicate) {
    Optional<Cards> cards = cardsList.stream().filter(predicate).findFirst();
    
    if (cards.isEmpty()) {
      throw new IllegalStateException();
    }
    
    return cards.get();
  }
}
