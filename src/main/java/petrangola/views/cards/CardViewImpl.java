package main.java.petrangola.views.cards;

import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public class CardViewImpl implements CardView {
  private static final String CARD_COVER = "/card_back";
  private static final String EXTENSION = ".png";
  
  private final ResourceService service;
  
  private Card card;
  private Pair<Vertical, Horizontal> position;
  private boolean isChosen;
  private ImageView imageView;
  
  public CardViewImpl(final Card card, final ResourceService service) {
    this.card = card;
    this.service = service;
    this.setPath(card);
    this.set();
  }
  
  private void setPath(final Card card) {
    this.service.setResourceName("/".concat(card.getFullName().concat(EXTENSION)));
  }
  
  @Override
  public void showCard() {
    this.card.setHidden(false);
    this.card.setCovered(false);
    this.get().setVisible(true);
    this.setPath(this.card);
    this.get().setImage(createImage(this.service.getPath()));
  }
  
  @Override
  public void updateCard(final Card card) {
    this.card = card;
    
    this.setPath(card);
    this.get().setImage(null);
    this.get().setImage(createImage(this.service.getPath()));
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
    return this.imageView;
  }
  
  private void set() {
    if (isCovered()) {
      this.service.setResourceName(CARD_COVER.concat(EXTENSION));
    }
    
    final ImageView imageView = new ImageView(createImage(this.service.getPath()));
    
    if (isHidden()) {
      imageView.setVisible(false);
    }
    
    this.imageView = imageView;
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
  public void effectsHandler() {
    final DropShadow pressEffect = new DropShadow( 20, Color.AQUA );
    
    this.get().pressedProperty().addListener((observable, eventHandler, t1) ->  {
        if (observable.getValue()) {
          this.get().setEffect(pressEffect);
        } else {
          this.get().setEffect(null);
        }
    });
    
    final DropShadow clickedEffect = new DropShadow(24, Color.CORAL);
    
    this.get().setOnMouseClicked(mouseEvent -> {
      if (this.isChosen()) {
        this.get().setEffect(clickedEffect);
      } else {
        this.get().setEffect(null);
      }
    });
  }
  
  
  @Override
  public Card getCard() {
    return this.card;
  }
  
  @Override
  public void setListeners() {
    this.get().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> toggleChosen());
  }
  
  @Override
  public void clearChosen() {
    this.get().setEffect(null);
    
    if (this.isChosen()) {
      this.toggleChosen();
    }
  }
}
