package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.components.textView.SimpleTextView;

public class OpponentsSizeCommand extends AbstractOptionCommand {
  private final SimpleTextView<Integer> opponentsSizeView;
  
  public OpponentsSizeCommand(final OptionController optionController, final SimpleTextView<Integer> opponentsSizeView) {
    super(optionController);
    this.opponentsSizeView = opponentsSizeView;
  }
  
  @Override
  public void execute() {
    this.optionController.setOpponentsSize(this.opponentsSizeView.getValue());
  }
}
