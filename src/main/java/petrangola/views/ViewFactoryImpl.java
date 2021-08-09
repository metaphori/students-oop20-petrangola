package main.java.petrangola.views;

import javafx.stage.Stage;
import main.java.petrangola.views.action.ActionViewImpl;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.game.GameViewImpl;
import main.java.petrangola.views.option.OptionView;
import main.java.petrangola.views.option.OptionViewImpl;

public class ViewFactoryImpl implements ViewFactory {
  @Override
  public GameView createGameView() {
    return new GameViewImpl();
  }
  
  @Override
  public OptionView createOptionView() {
    return new OptionViewImpl();
  }
  
  @Override
  public ActionView createActionView(Stage primaryStage) {
    return new ActionViewImpl(primaryStage);
  }
}
