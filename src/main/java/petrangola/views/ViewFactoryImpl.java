package main.java.petrangola.views;

import javafx.stage.Stage;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.views.action.ActionViewImpl;
import main.java.petrangola.views.game.GameViewImpl;
import main.java.petrangola.views.option.OptionViewImpl;

public class ViewFactoryImpl implements ViewFactory {
  private static Stage STAGE;
  
  public ViewFactoryImpl(Stage stage) {
    STAGE = stage;
  }
  
  public static Stage getStage() {
    return STAGE;
  }
  
  @Override
  public void createGameView(final Option option) {
    new GameViewImpl(getStage(), option);
  }
  
  @Override
  public void createOptionView() {
    new OptionViewImpl(getStage());
  }
  
  @Override
  public void createActionView() {
    new ActionViewImpl(getStage());
  }
}
