package main.java.petrangola.views.player;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.ViewFX;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.buttons.ExchangeButton;
import main.java.petrangola.views.player.buttons.KnockButton;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;

public class UserViewImpl extends AbstractPlayerViewImpl implements UserView {
  private final ExchangeButton exchangeButton;
  private final KnockButton knockButton;
  private CardsExchanged cardsExchanged;
  
  public UserViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(playerController, game, playerDetail, layout);
    
    this.exchangeButton = new ExchangeButton(playerController, playerDetail.getPlayer());
    this.knockButton = new KnockButton(playerController, playerDetail.getPlayer(), game);
  }
  
  @Override
  public CardsExchanged getCardsExchanged() {
    return this.cardsExchanged;
  }
  
  @Override
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  @Override
  public void register(Pane layout, LayoutBuilder layoutBuilder) {
    this.registerCards(layout, layoutBuilder);
    this.registerActions(layout);
  }
  
  @Override
  public ExchangeButton getExchangeButton() {
    return this.exchangeButton;
  }
  
  @Override
  public boolean isUser() {
    return true;
  }
  
  @Override
  public KnockButton getKnockButton() {
    return this.knockButton;
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "updatedCombination":
        this.onUpdatedCombination(this.getExchangeButton(), (Cards) evt.getSource());
        break;
      case "exchange":
        this.onExchange((List<Cards>) evt.getNewValue());
        break;
      case "playerLives":
        int playerLives = (int) evt.getNewValue();
        
        if (playerLives == 0) {
          System.exit(0);
        }
        
        break;
      default:
        super.propertyChange(evt);
    }
  }
  
  @Override
  protected void onExchange(List<Cards> cardsList) {
    this.clearChosenCards();
    super.onExchange(cardsList);
  }
  
  protected void registerCards(Pane layout, LayoutBuilder layoutBuilder) {
    final HBox cardsHBox = new HBox();
    final Pane userCardsPane = (Pane) layout.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
    userCardsPane.setManaged(true);
    userCardsPane.setVisible(true);
    // hide highCard Pane
    final Pane highCardPane = (Pane) layout.lookup(GameStyleClass.USER_HIGH_CARD.getAsStyleClass());
    highCardPane.setManaged(false);
    highCardPane.setVisible(false);
    
    final Pos cardsViewPosition = layoutBuilder.getPos(this.getCardsView().getPosition());
    cardsHBox.setAlignment(cardsViewPosition);
    cardsHBox.setSpacing(8);
    
    ViewFX.addOrUpdate(userCardsPane, cardsHBox);
    ViewFX.addOrUpdateAll(cardsHBox, this.getCardsView().get().getChildren());
  }
  
  protected void registerActions(Pane layout) {
    final Pane userActionsPane = (Pane) layout.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
    final AbstractButtonFX exchangeButton = this.getExchangeButton();
    final AbstractButtonFX knockButton = this.getKnockButton();
    
    exchangeButton.get().setDisable(true);
    knockButton.get().setDisable(false);
    
    final HBox actionsHBox = new HBox();
    final String actionsHBoxClass = "actionsBox";
    
    actionsHBox.getStyleClass().add(actionsHBoxClass);
    actionsHBox.setSpacing(24);
    actionsHBox.setAlignment(Pos.CENTER);
    
    ViewFX.addOrUpdate(userActionsPane, actionsHBox);
    ViewFX.addOrUpdateAll(actionsHBox, exchangeButton.get(), knockButton.get());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserViewImpl)) return false;
    if (!super.equals(o)) return false;
    UserViewImpl userView = (UserViewImpl) o;
    return getExchangeButton().equals(userView.getExchangeButton()) && getKnockButton().equals(userView.getKnockButton()) && Objects.equals(getCardsExchanged(), userView.getCardsExchanged());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getExchangeButton(), getKnockButton(), getCardsExchanged());
  }
}
