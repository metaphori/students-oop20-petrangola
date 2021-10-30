package petrangola.views.option.commands;

import petrangola.controllers.option.OptionController;
import petrangola.views.Command;

import java.util.Objects;

public abstract class AbstractOptionCommand implements Command {
  public final OptionController optionController;
  
  public AbstractOptionCommand(final OptionController optionController) {
    this.optionController = optionController;
  }
  
  public OptionController getOptionController() {
    return this.optionController;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractOptionCommand)) return false;
    AbstractOptionCommand that = (AbstractOptionCommand) o;
    return getOptionController().equals(that.getOptionController());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getOptionController());
  }
}
