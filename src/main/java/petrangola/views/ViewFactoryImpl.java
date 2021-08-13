package main.java.petrangola.views;

import javafx.stage.Stage;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.action.ActionViewImpl;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.game.GameViewImpl;
import main.java.petrangola.views.option.OptionView;
import main.java.petrangola.views.option.OptionViewImpl;

public class ViewFactoryImpl implements ViewFactory {
  private static Stage STAGE;
  
  public ViewFactoryImpl(Stage stage) {
    STAGE = stage;
  }
  
  @Override
  public GameView createGameView(String username, DifficultyLevel difficultyLevel, int opponentsSize) {
    return new GameViewImpl(getStage(), username, difficultyLevel, opponentsSize);
  }
  
  @Override
  public OptionView createOptionView() {
    return new OptionViewImpl(getStage());
  }
  
  @Override
  public ActionView createActionView() {
    return new ActionViewImpl(getStage());
  }
  
  public static Stage getStage() {
    return STAGE;
  }
}
