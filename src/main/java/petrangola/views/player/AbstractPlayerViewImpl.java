package main.java.petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.ViewFX;
import main.java.petrangola.views.animation.player.PlayerAnimation;
import main.java.petrangola.views.animation.player.PlayerAnimationImpl;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.game.GameStyleClass;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPlayerViewImpl implements PlayerView {
  private final PlayerAnimation playerAnimation = new PlayerAnimationImpl();
  private final PlayerDetail playerDetail;
  private final PlayerController playerController;
  private final Pane layout;
  private final LifeView lifeView = new LifeViewImpl(new Text());
  
  private CardsView<Group> cardsView;
  
  public AbstractPlayerViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, Pane layout) {
    this.playerDetail = playerDetail;
    this.playerController = playerController;
    this.layout = layout;
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
  public boolean isUser() {
    return false;
  }
  
  @Override
  public boolean isBoardView() {
    return false;
  }
  
  @Override
  public boolean isDealer() {
    return false;
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "firstExchange":
        this.onFirstExchange((List<Cards>) evt.getNewValue());
        break;
      case "exchange":
        this.onExchange((List<Cards>) evt.getNewValue());
        break;
    }
  }
  
  @Override
  public void updateLifeView(int playerLives) {
    final Pane lifePane = (Pane) layout.lookup(GameStyleClass.LIFE.getAsStyleClass());
    
    ViewFX.addOrUpdate(lifePane, getLifeView().get());
    
    getLifeView().updateOrCreateTextViewFX(getLayout(), GameStyleClass.LIFE.getAsStyleClass(), String.valueOf(playerLives));
  }
  
  @Override
  public PlayerController getPlayerController() {
    return this.playerController;
  }
  
  protected Pane getLayout() {
    return this.layout;
  }
  
  private LifeView getLifeView() {
    return this.lifeView;
  }
  
  protected PlayerAnimation getPlayerAnimation() {
    return this.playerAnimation;
  }
  
  protected void onExchange(List<Cards> cardsList) {
    this.updateCards(cardsList);
    this.getPlayerAnimation().onExchange(this.getPlayer(), cardsList);
  }
  
  private void onFirstExchange(List<Cards> cardsList) {
    this.updateCards(cardsList);
    this.getPlayerAnimation().onFirstExchange(this.getPlayer());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPlayerViewImpl)) return false;
    AbstractPlayerViewImpl that = (AbstractPlayerViewImpl) o;
    return getPlayerAnimation().equals(that.getPlayerAnimation()) && playerDetail.equals(that.playerDetail) && getPlayerController().equals(that.getPlayerController()) && getLayout().equals(that.getLayout()) && getLifeView().equals(that.getLifeView()) && Objects.equals(getCardsView(), that.getCardsView());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPlayerAnimation(), playerDetail, getPlayerController(), getLayout(), getLifeView(), getCardsView());
  }
}
