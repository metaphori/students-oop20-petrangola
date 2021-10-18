package main.java.petrangola.views.player;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.buttons.AcceptDealtCardsButton;
import main.java.petrangola.views.player.buttons.TakeBoardCardsButton;

public class DealerViewImpl extends AbstractPlayerViewImpl implements DealerView {
  private AbstractButtonFX acceptDealtCardsButton;
  private AbstractButtonFX firstExchangeButton;
  private GameController gameController;
  private CardsExchanged cardsExchanged;
  
  public DealerViewImpl(final DealerController dealerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(dealerController, game, playerDetail, layout);
  }
  
  @Override
  public void init(Cards boardCards) {
    this.acceptDealtCardsButton = new AcceptDealtCardsButton(this.getGameController());
    this.firstExchangeButton = new TakeBoardCardsButton(this.getGameController(), this.getDealerController(), boardCards, this.getCardsView().getCards());
    
    this.getAcceptDealtCardsButton().get().setVisible(true);
    this.getFirstExchangeButton().get().setVisible(true);
  }
  
  @Override
  public AbstractButtonFX getAcceptDealtCardsButton() {
    return this.acceptDealtCardsButton;
  }
  
  @Override
  public AbstractButtonFX getFirstExchangeButton() {
    return this.firstExchangeButton;
  }
  
  @Override
  public void showView(final Pane layout) {
    super.toggleButtonVisibility(true);
    this.dealerButtonsHandler(false, layout);
  }
  
  @Override
  public void hideView(final Pane layout) {
    super.toggleButtonVisibility(false);
    this.dealerButtonsHandler(true, layout);
    this.registerActions(layout);
  }
  
  @Override
  public void setGameController(GameController gameController) {
    this.gameController = gameController;
  }
  
  @Override
  public void register(Pane layout, LayoutBuilder layoutBuilder) {
    this.registerCards(layout, layoutBuilder);
  }
  
  @Override
  public CardsExchanged getCardsExchanged() {
    return this.cardsExchanged;
  }
  
  @Override
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  private GameController getGameController() {
    return this.gameController;
  }
  
  private DealerController getDealerController() {
    return (DealerController) getPlayerController();
  }
  
  private void dealerButtonsHandler(boolean hide, Pane layout) {
    final HBox dealerButtonHBox = new HBox();
    final AbstractButtonFX acceptDealtCardsButton = this.getAcceptDealtCardsButton();
    final AbstractButtonFX firstExchangeButton = this.getFirstExchangeButton();
    final boolean isVisible = !hide;
    
    acceptDealtCardsButton.get().setVisible(isVisible);
    firstExchangeButton.get().setVisible(isVisible);
    
    final Pane dealerButtonsPane = (Pane) layout.lookup(GameStyleClass.DEALER_BUTTONS.getAsStyleClass());
    
    if (!hide) {
      dealerButtonHBox.setAlignment(Pos.CENTER);
      dealerButtonHBox.setSpacing(8);
      
      dealerButtonHBox.getChildren().addAll(acceptDealtCardsButton.get(), firstExchangeButton.get());
      dealerButtonsPane.getChildren().add(dealerButtonHBox);
    } else {
      dealerButtonsPane.getChildren().clear();
    }
  }
  
  private void registerCards(Pane layout, LayoutBuilder layoutBuilder) {
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
  
  private void registerActions(Pane layout) {
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
