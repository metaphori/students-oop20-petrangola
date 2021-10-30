package petrangola.views.option.commands;

import petrangola.controllers.option.OptionController;
import petrangola.views.components.textFieldView.SimpleTextFieldView;

import java.util.Objects;

public class UsernameCommand extends AbstractOptionCommand {
  private final SimpleTextFieldView<String> usernameView;
  
  public UsernameCommand(final SimpleTextFieldView<String> usernameView, final OptionController optionController) {
    super(optionController);
    this.usernameView = usernameView;
  }
  
  @Override
  public void execute() {
    this.optionController.setUsername(this.usernameView.getValue());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UsernameCommand)) return false;
    UsernameCommand that = (UsernameCommand) o;
    return usernameView.equals(that.usernameView);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(usernameView);
  }
}
