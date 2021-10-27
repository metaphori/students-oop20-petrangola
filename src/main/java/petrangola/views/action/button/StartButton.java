package main.java.petrangola.views.action.button;


import main.java.petrangola.controllers.action.ActionController;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;

import java.util.Objects;

public class StartButton extends AbstractButtonFX {
  private static final String START = "Start";
  private final ActionController actionController;
  
  public StartButton(ActionController actionController) {
    super(START);
    this.actionController = actionController;
  }
  
  @Override
  public void handleStyle() {
    super.getStyleBuilder()
          .addStyle("-fx-padding: 8 15 15 15;" +
                          "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;" +
                          "-fx-background-radius: 8;" +
                          "-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a," +
                          "radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);" +
                          "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );" +
                          "-fx-font-weight: bold;" +
                          "-fx-font-size: 1.1em;" +
                          "-fx-text-fill: white;" +
                          "-fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );", UserAction.NOTHING)
          .addStyle("-fx-background-color:" +
                          "linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),#9d4024,#d86e3a," +
                          "radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);", UserAction.HOVER)
          .addStyle("-fx-padding: 10 15 13 15;-fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;", UserAction.PRESS);
    
    super.handleStyle();
    
    super.get().setMinWidth(ViewConstants.WIDTH.getLength() * 0.5);
    // super.get().setWidth(ViewConstants.WIDTH.getLength() * 0.6);
    super.get().setMaxWidth(ViewConstants.WIDTH.getLength() * 0.7);
  }
  
  @Override
  public void setListeners() {
    super.get().setOnMouseClicked(mouseEvent -> this.actionController.start());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StartButton)) return false;
    StartButton that = (StartButton) o;
    return actionController.equals(that.actionController);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(actionController);
  }
}
