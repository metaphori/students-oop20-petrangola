package main.java.petrangola.views.player.buttons;

import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;

public class KnockButton extends AbstractButtonFX {
  private static final String KNOCK = "Knock";
  private final Game game;
  private final PlayerController playerController;
  
  public KnockButton(PlayerController playerController, Game game) {
    super(KNOCK);
    this.game = game;
    this.playerController = playerController;
    this.setDisable(true);
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #FBAB7E 0%, #F7CE68 100%);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, #8BC6EC 0%, #9599E2 100%);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.25);
    // super.get().setWidth(ViewConstants.WIDTH.getLength() * 0.4);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.38);
  }
  
  @Override
  public void setListeners() {
    super.get().setOnMouseClicked(mouseEvent -> this.playerController.knock(this.game));
  }
}
