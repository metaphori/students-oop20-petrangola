package main.java.petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.events.NextRoundEvent;
import main.java.petrangola.views.events.NextTurnEvent;
import main.java.petrangola.views.player.buttons.ExchangeButton;
import main.java.petrangola.views.player.buttons.KnockButton;
import org.greenrobot.eventbus.EventBus;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class AbstractPlayerViewImpl implements PlayerView {
  protected final Game game;
  
  private final PlayerDetail playerDetail;
  private final PlayerController playerController;
  private final Pane layout;
  
  private AbstractButtonFX exchangeButton;
  private AbstractButtonFX knockButton;
  private CardsView<Group> cardsView;
  private BoardView boardView;
  
  public AbstractPlayerViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, Pane layout) {
    this.game = game;
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
  public void setCardsView(CardsView<Group> cardsView) {
    this.cardsView = cardsView;
  }
  
  public BoardView getBoardView() {
    return this.boardView;
  }
  
  @Override
  public void setBoardView(BoardView boardView) {
    this.boardView = boardView;
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
  public Player getPlayer() {
    return this.playerDetail.getPlayer();
  }
  
  
  protected PlayerController getPlayerController() {
    return this.playerController;
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "exchangedCards":
        CardsExchanged cardsExchanged = (CardsExchanged) evt.getNewValue();
        
        this.getExchangeButton().setData(cardsExchanged);
        this.enableExchangeButton(cardsExchanged, getExchangeButton());
        
        break;
      case "firstExchange":
        this.getBoardView().getCardsView().showCards();
        
        EventBus.getDefault().post(new NextRoundEvent());
        EventBus.getDefault().post(new NextTurnEvent());
        
        break;
      
      case "exchange":
        this.clearChosenCards();
        this.updateCards((List<Cards>) evt.getNewValue());
        
        EventBus.getDefault().post(new NextTurnEvent());
        
        break;
      case "playerLives":
        
        break;
    }
  }
  
  @Override
  public void toggleUserButton(Player player) {
    if (!player.isNPC()) {
      // if its not current player
      this.getExchangeButton().get().setVisible(true);
      this.getKnockButton().get().setVisible(true);
    } else {
      this.getExchangeButton().get().setVisible(false);
      this.getKnockButton().get().setVisible(false);
    }
  }
  
  public Pane getLayout() {
    return this.layout;
  }
  
  private void updateCards(List<Cards> cardsList) {
    cardsList.forEach(cards -> {
      if (cards.isCommunity()) {
        final CardsView<Group> boardCardsView = getBoardView().getCardsView();
        boardCardsView.setCards(cards);
        boardCardsView.update(cards);
      }
      
      if (cards.getPlayer().equals(getPlayer())) {
        getCardsView().setCards(cards);
        getCardsView().update(cards);
      }
    });
  }
  
  private boolean isUserPlayer() {
    return !this.getPlayer().isNPC();
  }
  
  private void clearChosenCards() {
    this.getExchangeButton().setData(null);
    this.getCardsView().getCardViews().forEach(CardView::clearChosen);
    this.getBoardView().getCardsView().getCardViews().forEach(CardView::clearChosen);
  }
  
  private void enableExchangeButton(CardsExchanged cardsExchanged, AbstractButtonFX exchangeButton) {
    cardsExchanged
          .getBoardCards()
          .ifPresent(boardCards -> {
            cardsExchanged
                  .getPlayerCards()
                  .ifPresent(playerCards -> exchangeButton.setDisable(!cardsExchanged.areExchangeable(boardCards, playerCards)));
          });
  }
}
