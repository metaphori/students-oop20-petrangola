package main.java.petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.text.TextViewImpl;
import main.java.petrangola.views.player.buttons.ExchangeButton;
import main.java.petrangola.views.player.buttons.KnockButton;

public class AbstractPlayerViewImpl implements PlayerView {
  protected final Game game;
  protected final CardsView<Group> cardsView;
  
  private final PlayerDetail playerDetail;
  private final TextViewImpl lifeView;
  private final TextViewImpl usernameView;
  
  private AbstractButtonFX exchangeButton;
  private AbstractButtonFX knockButton;
  
  public AbstractPlayerViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    this.game = game;
    this.playerDetail = playerDetail;
    this.cardsView = cardsView;
    this.lifeView = new LifeViewImpl(new Text(), this.playerDetail);
    this.usernameView = new UsernameViewImpl(new Text(), this.playerDetail.getPlayer());
    
    if (this.isUserPlayer()) {
      this.exchangeButton = new ExchangeButton(playerController, playerDetail.getPlayer());
      this.knockButton = new KnockButton(playerController, game);
    }
  }
  
  @Override
  public void showName() {
    this.usernameView.show();
  }
  
  @Override
  public void hideName() {
    this.usernameView.hide();
  }
  
  @Override
  public void showLives() {
    this.lifeView.show();
  }
  
  @Override
  public void hideLives() {
    this.lifeView.hide();
  }
  
  @Override
  public void showCards() {
    this.cardsView.showCards();
  }
  
  @Override
  public void showAction() {
    if (this.isUserPlayer()) {
      this.exchangeButton.setDisable(false);
      this.knockButton.setDisable(false);
    }
  }
  
  @Override
  public CardsView<Group> getCardsView() {
    return this.cardsView;
  }
  
  @Override
  public AbstractButtonFX getExchangeButton() {
    if (this.isUserPlayer()) {
      return this.exchangeButton;
    }
    
    return null;
  }
  
  @Override
  public AbstractButtonFX getKnockButton() {
    if (this.isUserPlayer()) {
      return this.knockButton;
    }
    
    return null;
  }
  
  @Override
  public LifeView getLifeView() {
    return (LifeView) this.lifeView;
  }
  
  @Override
  public UsernameView getUsernameView() {
    return (UsernameView) this.usernameView;
  }
  
  @Override
  public Player getPlayer() {
    return this.playerDetail.getPlayer();
  }
  
  private boolean isUserPlayer() {
    return !this.getPlayer().isNPC();
  }
}
