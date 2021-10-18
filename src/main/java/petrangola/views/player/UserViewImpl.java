package main.java.petrangola.views.player;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;

public class UserViewImpl extends AbstractPlayerViewImpl implements UserView {
  private CardsExchanged cardsExchanged;
  
  public UserViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(playerController, game, playerDetail, layout);
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
    cardsHBox.getChildren().addAll(this.getCardsView().get().getChildren());
  
    userCardsPane.getChildren().add(cardsHBox);
  }
  
  protected void registerActions(Pane layout) {
    final HBox actionsHBox = new HBox();
    final Pane userActionsPane = (Pane) layout.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
    final AbstractButtonFX exchangeButton = this.getExchangeButton();
    final AbstractButtonFX knockButton = this.getKnockButton();
  
    exchangeButton.get().setDisable(true);
    knockButton.get().setDisable(false);
  
    actionsHBox.getChildren().addAll(exchangeButton.get(), knockButton.get());
    actionsHBox.setSpacing(24);
    actionsHBox.setAlignment(Pos.CENTER);
  
    userActionsPane.getChildren().add(actionsHBox);
  }
}
