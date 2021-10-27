package main.java.petrangola.views.cards;

import javafx.scene.Group;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.services.ResourceServiceImpl;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

import java.util.Objects;

public class CardsViewFactoryImpl implements CardsViewFactory {
  private final ResourceService service = new ResourceServiceImpl();
  
  @Override
  public CardsView<Group> createUserCards(Cards cards, Pair<Vertical, Horizontal> position) {
    return new CardsViewImpl(this.service, cards, position, false);
  }
  
  @Override
  public CardsView<Group> createOpponentCards(Cards cards, int npcIndex, int thresholdNpc) {
    cards.getCombination().getCards().forEach(card -> card.setCovered(true));
    
    final Pair<Vertical, Horizontal> position = npcIndex < thresholdNpc ? new Pair<>(Vertical.TOP, Horizontal.LEFT) : new Pair<>(Vertical.TOP, Horizontal.RIGHT);
    
    return new CardsViewImpl(this.service, cards, position, true);
  }
  
  @Override
  public CardsView<Group> createBoardCards(Cards cards, Pair<Vertical, Horizontal> position) {
    cards.getCombination().getCards().stream().skip(2).forEach(card -> card.setCovered(true));
    return new CardsViewImpl(this.service, cards, position, false);
  }
  
  @Override
  public CardsView<Group> createDealerCards(Cards cards, Pair<Vertical, Horizontal> position) {
    cards.getCombination().getCards().stream().limit(2).forEach(card -> card.setCovered(true));
    cards.getCombination().getCards().stream().skip(2).forEach(card -> card.setHidden(true));
    
    final boolean areListenerDisabled = cards.getPlayer().get().isNPC();
    
    return new CardsViewImpl(this.service, cards, position, areListenerDisabled);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardsViewFactoryImpl)) return false;
    CardsViewFactoryImpl that = (CardsViewFactoryImpl) o;
    return service.equals(that.service);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(service);
  }
}
