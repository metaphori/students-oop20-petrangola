package main.java.petrangola.views.player.buttons;

import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.player.commands.ExchangeCommand;

public class ExchangeButton extends AbstractButtonFX {
  private static final String EXCHANGE = "Exchange";
  private final ExchangeCommand command;
  
  public ExchangeButton(final PlayerController playerController, final Player player) {
    super(EXCHANGE);
    this.command = new ExchangeCommand(playerController, player);
    this.setDisable(true);
    this.get().getStyleClass().add(EXCHANGE);
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #ff9a9e 0%, #fad0c4 99%, #fad0c4 100%);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color:  linear-gradient(from 0% 93% to 0% 100%, #ffb1de 0%, #fab2c4 99%, #fad0c4 100%);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.25);
    // super.get().setWidth(ViewConstants.WIDTH.getLength() * 0.4);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.38);
  }
  
  @Override
  public void setListeners() {
    super.get().setOnMouseClicked(mouseEvent -> {
      final CardsExchanged cardsExchanged = (CardsExchanged) this.getData();
      
      if (cardsExchanged != null) {
        this.command.setCardsExchanged(cardsExchanged);
      }
      
      if (this.command.check()) {
        this.command.execute();
      }
    });
  }
}
