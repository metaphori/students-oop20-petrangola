package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;

public abstract class AbstractOptionCommand {
  public OptionController optionController;
  
  public AbstractOptionCommand(final OptionController optionController) {
    this.optionController = optionController;
  }
  
  public abstract void execute();
}
