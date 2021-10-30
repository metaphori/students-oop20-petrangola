package petrangola.views.game.buttons;

import petrangola.models.game.Game;
import petrangola.utlis.UserAction;
import petrangola.utlis.ViewConstants;
import petrangola.views.events.ReplayEvent;
import petrangola.views.components.button.AbstractButtonFX;
import petrangola.views.game.GameStyleClass;
import petrangola.views.mediator.GameMediator;
import org.greenrobot.eventbus.EventBus;

public class ReplayButton extends AbstractButtonFX {
  private static final String REPLAY_TEXT = " Continue play → ";
  private final Game game;
  private final GameMediator gameMediator;
  
  public ReplayButton(Game game, GameMediator gameMediator) {
    super(REPLAY_TEXT);
    this.game = game;
    this.gameMediator = gameMediator;
    this.get().getStyleClass().add(GameStyleClass.REPLAY_CLASS.toString());
    this.hideButton(false);
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #4a2193 0%, #7548c7 99%, #9979d4 100%);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color:  linear-gradient(from 0% 93% to 0% 100%, #7398ff 0%, #3e63c9 99%, #0b1d4d 100%);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.45);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.68);
  }
  
  @Override
  public void setListeners() {
    super.get().setOnMouseClicked(mouseEvent -> {
      this.hideButton(true);
      EventBus.getDefault().post(new ReplayEvent(this.getGame(), this.getGameMediator()));
    });
  }
  
  private GameMediator getGameMediator() {
    return this.gameMediator;
  }
  
  private Game getGame() {
    return this.game;
  }
  
  private void hideButton(boolean hide) {
    super.get().setDisable(hide);
    super.get().setManaged(!hide);
    super.get().setVisible(!hide);
  }
}
