package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.controllers.option.OptionControllerImpl;

public abstract class AbstractOptionCommand {
  public final OptionController optionController = new OptionControllerImpl();
  
  public AbstractOptionCommand() {}
  
  public OptionController getOptionController() {
    return this.optionController;
  }
  
  public abstract void execute();
}
