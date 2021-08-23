package main.java.petrangola.views.cards;

import javafx.scene.Group;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.components.ViewNode;

import java.util.List;
import java.util.stream.Collectors;

public class CardsViewImpl implements CardsView<Group> {
  private final Cards cards;
  private final ResourceService service;
  private List<CardView> cardsViews;
  private Pair<Vertical, Horizontal> position;
  
  public CardsViewImpl(ResourceService service, Cards cards, Pair<Vertical, Horizontal> position) {
    this.cards = cards;
    this.service = service;
    this.position = position;
    
    this.set();
    this.addListeners();
  }
  
  @Override
  public void showCards() {
    this.cardsViews.forEach(CardView::showCard);
  }
  
  @Override
  public List<CardView> getCardsViews() {
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
    this.cardsViews.forEach(cardView -> cardView.get().setOnMouseClicked(mouseEvent -> cardView.toggleChosen()));
  }
  
  @Override
  public Group get() {
    return new Group(this.cardsViews.stream().map(ViewNode::get).collect(Collectors.toList()));
  }
}
