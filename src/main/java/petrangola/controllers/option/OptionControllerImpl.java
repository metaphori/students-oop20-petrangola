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
  
  @Override
  public void play(Object... data) {
    String username = "";
    DifficultyLevel level = null;
    int size = 0;
    
    for (Object o : data) {
      if (o instanceof String) {
        username = o.toString();
      }
      
      if (o instanceof DifficultyLevel) {
        level = (DifficultyLevel) o;
      }
      
      if (o instanceof Integer) {
        size = (int) o;
      }
    }
    
    this.viewFactory.createGameView(username, level, size);
  }
  
}
