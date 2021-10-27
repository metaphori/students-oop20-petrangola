package main.java.petrangola.views.cards;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.utlis.DeckConstants;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.components.ViewNode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CardsViewImpl implements CardsView<Group> {
  private final ResourceService service;
  private Cards cards;
  private List<CardView> cardsViews;
  private Pair<Vertical, Horizontal> position;
  
  public CardsViewImpl(ResourceService service, Cards cards, Pair<Vertical, Horizontal> position, boolean areListenerDisabled) {
    this.cards = cards;
    this.service = service;
    this.position = position;
    
    this.set();
    
    if (!areListenerDisabled) {
      this.addListeners();
    }
  }
  
  @Override
  public void showCards() {
    this.cardsViews.forEach(CardView::showCard);
  }
  
  @Override
  public List<CardView> getCardViews() {
    return this.cardsViews;
  }
  
  @Override
  public Cards getCards() {
    return this.cards;
  }
  
  @Override
  public void setCards(final Cards cards) {
    this.cards = cards;
  }
  
  @Override
  public void update(Cards cards) {
    for (int index = 0; index < DeckConstants.DECK_SIZE.getValue(); index++) {
      final Card cardToUpdate = getCards().getCombination().getCards().get(index);
      final boolean isShowing = getCards().isCommunity() || (getCards().getPlayer().isPresent() && !getCards().getPlayer().get().isNPC());
      
      this.getCardViews().get(index).updateCard(cardToUpdate, isShowing);
    }
  }
  
  @Override
  public Pair<Vertical, Horizontal> getPosition() {
    return this.position;
  }
  
  @Override
  public void setPosition(Pair<Vertical, Horizontal> position) {
    this.position = position;
  }
  
  public void set() {
    this.cardsViews = this.cards
                            .getCombination()
                            .getCards()
                            .stream()
                            .map(card -> new CardViewImpl(card, this.service))
                            .collect(Collectors.toList());
  }
  
  private void addListeners() {
    this.cardsViews.forEach(cardView -> {
      cardView.setListeners();
      cardView.effectsHandler();
    });
  }
  
  @Override
  public Group get() {
    return new Group(this.cardsViews.stream().map(ViewNode::get).toArray(ImageView[]::new));
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardsViewImpl)) return false;
    CardsViewImpl cardsView = (CardsViewImpl) o;
    return service.equals(cardsView.service) && getCards().equals(cardsView.getCards()) && cardsViews.equals(cardsView.cardsViews) && getPosition().equals(cardsView.getPosition());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(service, getCards(), cardsViews, getPosition());
  }
}
