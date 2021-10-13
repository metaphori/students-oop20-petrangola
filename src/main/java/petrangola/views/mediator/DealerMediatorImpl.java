package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.DealerView;

public class DealerMediatorImpl implements DealerMediator {
  private DealerView dealerView;
  
  public DealerMediatorImpl(DealerView dealerView) {
    this.dealerView = dealerView;
  }
  
  @Override
  public void initDealerView(GameController gameController, DealerController dealerController, Cards boardCards) {
    getDealerView().init(gameController, dealerController, boardCards);
  }
  
  @Override
  public void showDealerView(final Pane layout) {
    toggleButtonVisibility(true);
    dealerButtonsHandler(false, layout);
  }
  
  @Override
  public void hideDealerView(final Pane layout) {
    toggleButtonVisibility(false);
    dealerButtonsHandler(true, layout);
  }
  
  @Override
  public void onDealtCards() {
  
  }
  
  private void toggleButtonVisibility(boolean hide) {
    getDealerView().getExchangeButton().setDisable(hide);
    getDealerView().getKnockButton().setDisable(hide);
    getDealerView().getExchangeButton().get().setVisible(!hide);
    getDealerView().getKnockButton().get().setVisible(!hide);
  }
  
  private void dealerButtonsHandler(boolean hide, Pane layout) {
    final AbstractButtonFX acceptDealtCardsButton = getDealerView().getAcceptDealtCardsButton();
    final AbstractButtonFX firstExchangeButton = getDealerView().getFirstExchangeButton();
  
    acceptDealtCardsButton.get().setVisible(hide);
    firstExchangeButton.get().setVisible(hide);
  
    final Pane dealerButtonsPane = (Pane) layout.lookup(GameStyleClass.DEALER_BUTTONS.getAsStyleClass());
    
    if (!hide) {
      dealerButtonsPane.getChildren().addAll(acceptDealtCardsButton.get(), firstExchangeButton.get());
    } else {
      dealerButtonsPane.getChildren().clear();
    }
  }
  
  private DealerView getDealerView() {
    return this.dealerView;
  }
}
