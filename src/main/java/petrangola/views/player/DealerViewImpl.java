package main.java.petrangola.views.player;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.ViewFX;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.buttons.AcceptDealtCardsButton;
import main.java.petrangola.views.player.buttons.TakeBoardCardsButton;

import java.util.Objects;

public class DealerViewImpl extends UserViewImpl implements DealerView {
  private AbstractButtonFX acceptDealtCardsButton;
  private AbstractButtonFX firstExchangeButton;
  private GameController gameController;
  private CardsExchanged cardsExchanged;
  
  public DealerViewImpl(final DealerController dealerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(dealerController, game, playerDetail, layout);
  }
  
  @Override
  public boolean isDealer() {
    return true;
  }
  
  @Override
  public boolean isUser() {
    return false;
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
    this.setUserActionVisibility(false);
    this.dealerButtonsHandler(false, layout);
  }
  
  @Override
  public void hideView(final Pane layout) {
    this.setUserActionVisibility(true);
    this.dealerButtonsHandler(true, layout);
    this.registerActions(layout);
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
  
  @Override
  public void setGameController(GameController gameController) {
    this.gameController = gameController;
  }
  
  private void setUserActionVisibility(boolean isVisible) {
    this.getExchangeButton().get().setVisible(isVisible);
    this.getKnockButton().get().setVisible(isVisible);
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
      
      ViewFX.addOrUpdateAll(dealerButtonHBox, acceptDealtCardsButton.get(), firstExchangeButton.get());
      ViewFX.addOrUpdate(dealerButtonsPane, dealerButtonHBox);
    } else {
      dealerButtonsPane.getChildren().clear();
    }
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DealerViewImpl)) return false;
    if (!super.equals(o)) return false;
    DealerViewImpl that = (DealerViewImpl) o;
    return Objects.equals(getAcceptDealtCardsButton(), that.getAcceptDealtCardsButton()) && Objects.equals(getFirstExchangeButton(), that.getFirstExchangeButton()) && Objects.equals(getGameController(), that.getGameController()) && Objects.equals(getCardsExchanged(), that.getCardsExchanged());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getAcceptDealtCardsButton(), getFirstExchangeButton(), getGameController(), getCardsExchanged());
  }
}
