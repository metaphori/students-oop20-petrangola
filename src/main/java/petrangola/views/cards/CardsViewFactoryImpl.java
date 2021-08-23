package main.java.petrangola.views.cards;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.services.ResourceServiceImpl;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

import java.util.List;

public class CardsViewFactoryImpl implements CardsViewFactory {
  private final ResourceService service = new ResourceServiceImpl();
  
  @Override
  public CardsView<List<CardView>> createUserCards(Cards cards, Pair<Vertical, Horizontal> position) {
    return new CardsViewImpl(this.service, cards, position);
  }
  
  @Override
  public CardsView<List<CardView>> createOpponentCards(Cards cards, int npcIndex, int thresholdNpc) {
    cards.getCombination().getCards().forEach(card -> card.setCovered(true));
    
    final Pair<Vertical, Horizontal> position = npcIndex < thresholdNpc ? new Pair<>(Vertical.TOP, Horizontal.LEFT) : new Pair<>(Vertical.TOP, Horizontal.RIGHT);
    
    return new CardsViewImpl(this.service, cards, position);
  }
  
  // TODO: if nothing changes, just make one method called "simple"
  @Override
  public CardsView<List<CardView>> createBoardCards(Cards cards, Pair<Vertical, Horizontal> position) {
    return new CardsViewImpl(this.service, cards, position);
  }
  
  @Override
  public CardsView<List<CardView>> createDealerCards(Cards cards, Pair<Vertical, Horizontal> position) {
    cards.getCombination().getCards().stream().limit(2).forEach(card -> card.setCovered(true));
    cards.getCombination().getCards().stream().skip(2).forEach(card -> card.setHidden(true));
    
    return new CardsViewImpl(this.service, cards, position);
  }
}
