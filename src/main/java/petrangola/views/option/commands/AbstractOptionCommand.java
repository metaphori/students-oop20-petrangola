package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.Command;

public abstract class AbstractOptionCommand implements Command {
  public final OptionController optionController;
  
  public AbstractOptionCommand(final OptionController optionController) {
    this.optionController = optionController;
  }
  
  public OptionController getOptionController() {
    return this.optionController;
  }
}
