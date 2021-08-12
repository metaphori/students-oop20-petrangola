package main.java.petrangola.views.option.commands;

import main.java.petrangola.views.components.textView.SimpleTextView;

public class UsernameCommand extends AbstractOptionCommand {
  private final SimpleTextView<String> usernameView;
  
  public UsernameCommand(final SimpleTextView<String> usernameView) {
    this.usernameView = usernameView;
  }
  
  @Override
  public void execute() {
    this.optionController.setUsername(this.usernameView.getValue());
  }
}
