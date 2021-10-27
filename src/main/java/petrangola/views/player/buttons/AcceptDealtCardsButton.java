package main.java.petrangola.views.player.buttons;


import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;

import java.util.Objects;

public class AcceptDealtCardsButton extends AbstractButtonFX {
  private static final String FIRST_CHANGE = "Take your cards";
  private final GameController gameController;
  
  public AcceptDealtCardsButton(final GameController gameController) {
    super(FIRST_CHANGE);
    this.gameController = gameController;
    
    this.get().setVisible(false);
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #11ab4a 0%, #fad0c4 99%, #1bafca 100%);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color:  linear-gradient(from 0% 93% to 0% 100%, #046327 0%, #7db2e3 99%, #7db2e3 100%);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.25);
    // super.get().setWidth(ViewConstants.WIDTH.getLength() * 0.4);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.38);
  }
  
  @Override
  public void setListeners() {
    this.get().setOnMouseClicked(mouseEvent -> {
      this.gameController.roundHandler();
      this.gameController.nextTurnNumberHandler();
    });
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AcceptDealtCardsButton)) return false;
    if (!super.equals(o)) return false;
    AcceptDealtCardsButton that = (AcceptDealtCardsButton) o;
    return gameController.equals(that.gameController);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), gameController);
  }
}
