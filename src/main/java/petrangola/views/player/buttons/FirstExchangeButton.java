package main.java.petrangola.views.player.buttons;

import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public class FirstExchangeButton extends AbstractButtonFX {
  private static final String FIRST_CHANGE = "Take board cards";
  
  private final DealerController dealerController;
  private final Cards boardCards;
  private final Cards ownCards;
  
  public FirstExchangeButton(final DealerController dealerController, final Cards boardCards, final Cards ownCards) {
    super(FIRST_CHANGE);
    
    this.dealerController = dealerController;
    this.boardCards = boardCards;
    this.ownCards = ownCards;
    this.get().setVisible(false);
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #ffab00 0%, #c9a04d 99%, #c29f97 100%);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color:  linear-gradient(from 0% 93% to 0% 100%, #631504 0%, #7d301f 99%, #cc5439 100%);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.25);
    // super.get().setWidth(ViewConstants.WIDTH.getLength() * 0.4);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.38);
  }
  
  @Override
  public void setListeners() {
    this.get().setOnMouseClicked(mouseEvent -> this.dealerController.cherryPickingCombination(this.boardCards, this.ownCards));
  }
}
