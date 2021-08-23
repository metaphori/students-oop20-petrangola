package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class CardsExchangedImpl implements CardsExchanged {
  private final List<Cards> cardsList = new CopyOnWriteArrayList<>();
  
  @Override
  public void addCards(Cards cards) {
    this.cardsList.remove(cards);
    this.cardsList.add(cards);
  }
  
  @Override
  public Optional<Cards> getPlayerCards() {
    return cardsList
                 .stream()
                 .filter(Cards::isPlayerCards)
                 .findFirst();
  }
  
  @Override
  public Optional<Cards> getBoardCards() {
    return cardsList
                 .stream()
                 .filter(Cards::isCommunity)
                 .findFirst();
  }
}
