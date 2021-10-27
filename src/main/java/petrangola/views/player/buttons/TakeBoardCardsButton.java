package main.java.petrangola.views.player.buttons;

import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;

import java.util.Objects;

public class TakeBoardCardsButton extends AbstractButtonFX {
  private static final String FIRST_CHANGE = "Take board cards";
  
  private final GameController gameController;
  private final DealerController dealerController;
  private final Cards boardCards;
  private final Cards ownCards;
  
  public TakeBoardCardsButton(GameController gameController, final DealerController dealerController, final Cards boardCards, final Cards ownCards) {
    super(FIRST_CHANGE);
    
    this.gameController = gameController;
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
    this.get().setOnMouseClicked(mouseEvent -> {
      this.gameController.onlyOneRound();
      this.dealerController.cherryPickingCombination(this.boardCards, this.ownCards);
    });
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TakeBoardCardsButton)) return false;
    if (!super.equals(o)) return false;
    TakeBoardCardsButton that = (TakeBoardCardsButton) o;
    return gameController.equals(that.gameController) && dealerController.equals(that.dealerController) && boardCards.equals(that.boardCards) && ownCards.equals(that.ownCards);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), gameController, dealerController, boardCards, ownCards);
  }
}
