package main.java.petrangola.views.cards;

import javafx.scene.image.ImageView;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public class CardViewImpl implements CardView {
  private static final String CARD_COVER = "/card_back";
  private static final String EXTENSION = ".png";
  
  private final Card card;
  private final ResourceService service;
  private Pair<Vertical, Horizontal> position;
  private boolean isChosen;
  
  public CardViewImpl(final Card card, final ResourceService service) {
    this.card = card;
    this.service = service;
    this.service.setResourceName("/".concat(card.getFullName().concat(EXTENSION)));
  }
  
  @Override
  public void showCard() {
    this.card.setHidden(false);
    this.card.setCovered(false);
  }
  
  @Override
  public boolean isCovered() {
    return this.card.isCovered();
  }
  
  @Override
  public boolean isHidden() {
    return this.card.isHidden();
  }
  
  @Override
  public ImageView get() {
    if (isCovered()) {
      this.service.setResourceName(CARD_COVER.concat(EXTENSION));
    }
    
    final ImageView imageView = new ImageView(createImage(this.service.getPath()));
    
    if (isHidden()) {
      imageView.setVisible(false);
    }
    
    return imageView;
  }
  
  @Override
  public Pair<Vertical, Horizontal> getPosition() {
    return this.position;
  }
  
  @Override
  public void setPosition(Pair<Vertical, Horizontal> position) {
    this.position = position;
  }
  
  @Override
  public boolean isChosen() {
    return this.isChosen;
  }
  
  @Override
  public void toggleChosen() {
    this.isChosen = !this.isChosen();
    this.card.setChosen(this.isChosen);
  }
  
  @Override
  public Card getCard() {
    return this.card;
  }
}
