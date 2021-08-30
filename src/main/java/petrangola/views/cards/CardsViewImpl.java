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
  public void highlightBestCardsCombination() {
    // TODO: I don't know If I'll get to do it
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
  public void update(final Cards cards) {
    List<Card> cardList = cards.getCombination().getCards();
    
    
    for (int index = 0; index < DeckConstants.DECK_SIZE.getValue(); index++) {
      this.getCardViews().get(index).updateCard(cardList.get(index));
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
}
