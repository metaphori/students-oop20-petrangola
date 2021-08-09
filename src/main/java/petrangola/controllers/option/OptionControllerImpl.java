package main.java.petrangola.controllers.option;

import main.java.petrangola.models.option.OptionImpl;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.ViewFactoryImpl;


public class OptionControllerImpl implements OptionController {
  private final OptionImpl option;
  private final ViewFactory viewFactory;
  
  public OptionControllerImpl() {
    this.option = new OptionImpl();
    this.viewFactory = new ViewFactoryImpl();
  }
  
  @Override
  public void setOpponentsSize(int opponentsSize) {
    this.option.setOpponentSize(opponentsSize);
  }
  
  @Override
  public void setDifficulty(DifficultyLevel difficulty) {
    this.option.setDifficultyLevel(difficulty);
  }
  
  @Override
  public void setUsername(String username) {
    this.option.setUsername(username);
  }
  
  @Override
  public void play() {
    this.viewFactory.createGameView();
  }
  
}
