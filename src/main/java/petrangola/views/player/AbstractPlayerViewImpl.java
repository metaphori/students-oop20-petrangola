package main.java.petrangola.views.player;

import javafx.scene.text.Text;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.player.buttons.ExchangeButton;
import main.java.petrangola.views.player.buttons.KnockButton;

import java.util.List;

public class AbstractPlayerViewImpl implements PlayerView {
  private final PlayerDetail playerDetail;
  private final CardsView<List<CardView>> cardsView;
  private final LifeView lifeView = new LifeViewImpl(new Text());
  private AbstractButtonFX exchangeButton;
  private AbstractButtonFX knockButton;
  
  public AbstractPlayerViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final CardsView<List<CardView>> cardsView, CardsExchanged cardsExchanged) {
    this.playerDetail = playerDetail;
    this.cardsView = cardsView;
    
    if (this.isPlayer()) {
      this.exchangeButton = new ExchangeButton(playerController, playerDetail.getPlayer());
      this.knockButton = new KnockButton(playerController, game);
      this.exchangeButton.setData(cardsExchanged);
    }
  }
  
  @Override
  public void showName() {
    this.playerDetail.getPlayer().getUsername();
  }
  
  @Override
  public void showLives() {
    this.lifeView.setLives(this.playerDetail.getPlayerLives());
  }
  
  @Override
  public void showCards() {
    this.cardsView.showCards();
  }
  
  @Override
  public void showAction() {
    if (this.isPlayer()) {
      this.exchangeButton.setDisable(false);
      this.knockButton.setDisable(false);
    }
  }
  
  @Override
  public CardsView<List<CardView>> getCardsView() {
    return this.cardsView;
  }
  
  @Override
  public AbstractButtonFX getExchangeButton() {
    if (this.isPlayer()) {
      return this.exchangeButton;
    }
    
    return null;
  }
  
  @Override
  public AbstractButtonFX getKnockButton() {
    if (this.isPlayer()) {
      return this.knockButton;
    }
    
    return null;
  }
  
  @Override
  public LifeView getLifeView() {
    return this.lifeView;
  }
  
  private boolean isPlayer() {
    return !this.playerDetail.getPlayer().isNPC();
  }
}
