package main.java.petrangola.controllers.option;

import main.java.petrangola.models.option.Option;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.ViewFactoryImpl;


public class OptionControllerImpl implements OptionController {
  private final Option option;
  private final ViewFactory viewFactory;
  
  public OptionControllerImpl(final Option option) {
    this.option = option;
    this.viewFactory = new ViewFactoryImpl(ViewFactoryImpl.getStage());
  }
  
  @Override
  public void setOpponentsSize(int opponentsSize) {
    this.option.setOpponentsSize(opponentsSize);
  }
  
  @Override
  public void setDifficulty(DifficultyLevel difficulty) {
    this.option.setDifficultyLevel(difficulty);
  }
  
  @Override
  public void setUsername(String username) {
    this.option.setUsername(username);
  }
  
  
  public void play(final Option option) {
    this.viewFactory.createGameView(option);
  }
  
}
