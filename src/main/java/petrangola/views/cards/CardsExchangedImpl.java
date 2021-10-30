package petrangola.views.cards;

import petrangola.models.cards.Cards;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class CardsExchangedImpl implements CardsExchanged {
  private final Set<Cards> cardsList = new HashSet<>();
  
  @Override
  public void addCards(Cards cards) {
    this.cardsList.add(cards);
  }
  
  @Override
  public Optional<Cards> getPlayerCards() {
    return getOptionalCards(Cards::isPlayerCards);
  }
  
  @Override
  public Optional<Cards> getBoardCards() {
    return getOptionalCards(Cards::isCommunity);
  }
  
  private Optional<Cards> getOptionalCards(Predicate<? super Cards> predicate) {
    if (cardsList.stream().noneMatch(predicate)) {
      return Optional.empty();
    }
    
    return cardsList.stream().filter(predicate).findFirst();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardsExchangedImpl)) return false;
    CardsExchangedImpl that = (CardsExchangedImpl) o;
    return cardsList.equals(that.cardsList);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(cardsList);
  }
}
