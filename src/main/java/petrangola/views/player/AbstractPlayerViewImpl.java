package main.java.petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.cards.UpdatableCombination;
import main.java.petrangola.views.events.NextRoundEvent;
import main.java.petrangola.views.events.NextTurnEvent;
import main.java.petrangola.views.player.buttons.ExchangeButton;
import main.java.petrangola.views.player.buttons.KnockButton;
import org.greenrobot.eventbus.EventBus;

import java.beans.PropertyChangeEvent;
import java.util.List;

public abstract class AbstractPlayerViewImpl implements PlayerView {
  private final PlayerDetail playerDetail;
  private final PlayerController playerController;
  private final Pane layout;
  
  private ExchangeButton exchangeButton;
  private KnockButton knockButton;
  private CardsView<Group> cardsView;
  
  public AbstractPlayerViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, Pane layout) {
    this.playerDetail = playerDetail;
    this.playerController = playerController;
    this.layout = layout;
    
    if (this.isUserPlayer()) {
      this.exchangeButton = new ExchangeButton(playerController, playerDetail.getPlayer());
      this.knockButton = new KnockButton(playerController, game);
    }
  }
  
  @Override
  public void showCards() {
    this.cardsView.showCards();
  }
  
  @Override
  public CardsView<Group> getCardsView() {
    return this.cardsView;
  }
  
  @Override
  public void setCardsView(CardsView<Group> cardsView) {
    this.cardsView = cardsView;
  }
  
  @Override
  public ExchangeButton getExchangeButton() {
    if (this.isUserPlayer()) {
      return this.exchangeButton;
    }
    
    return null;
  }
  
  @Override
  public KnockButton getKnockButton() {
    if (this.isUserPlayer()) {
      return this.knockButton;
    }
    
    return null;
  }
  
  @Override
  public Player getPlayer() {
    return this.playerDetail.getPlayer();
  }
  
  @Override
  public void updateCards(List<Cards> cardsList) {
    cardsList.forEach(cards -> cards.getPlayer().ifPresent(player -> {
      if (!player.equals(getPlayer())) {
        return;
      }
      
      this.removeListenerModel(getCardsView().getCards());
      this.getCardsView().setCards(cards);
      this.getCardsView().update(cards);
      this.addListenerToModel(cards);
    }));
  }
  
  @Override
  public void toggleUserButton(Player player) {
    toggleButtonVisibility(player.isNPC());
  }
  
  public void toggleButtonVisibility(boolean hide) {
    this.getExchangeButton().setDisable(hide);
    this.getKnockButton().setDisable(hide);
    this.getExchangeButton().get().setVisible(!hide);
    this.getKnockButton().get().setVisible(!hide);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "updatedCombination":
        if (this.isUserPlayer()) {
          ((UpdatableCombination) this).onUpdatedCombination(this.getExchangeButton(), (Cards) evt.getSource());
        }
        
        break;
      case "firstExchange":
        this.onFirstExchange((List<Cards>) evt.getNewValue());
        break;
      case "exchange":
        this.onExchange((List<Cards>) evt.getNewValue());
        break;
      case "playerLives":
        
        break;
    }
  }
  
  @Override
  public PlayerController getPlayerController() {
    return this.playerController;
  }
  
  protected Pane getLayout() {
    return this.layout;
  }
  
  private boolean isUserPlayer() {
    return !this.getPlayer().isNPC();
  }
  
  private void onExchange(List<Cards> cardsList) {
    if (this.isUserPlayer()) {
      this.clearChosenCards();
    }
    
    this.updateCards(cardsList);
    EventBus.getDefault().post(new NextTurnEvent());
  }
  
  private void onFirstExchange(List<Cards> cardsList) {
    this.updateCards(cardsList);
    EventBus.getDefault().post(new NextRoundEvent());
    EventBus.getDefault().post(new NextTurnEvent());
  }
}
