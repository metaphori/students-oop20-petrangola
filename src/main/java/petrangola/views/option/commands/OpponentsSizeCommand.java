package main.java.petrangola.views.option.commands;

import main.java.petrangola.views.components.slider.SimpleSlider;

public class OpponentsSizeCommand extends AbstractOptionCommand {
  private final SimpleSlider<Integer> opponentsSizeView;
  
  public OpponentsSizeCommand(final SimpleSlider<Integer> opponentsSizeView) {
    this.opponentsSizeView = opponentsSizeView;
  }
  
  @Override
  public void execute() {
    this.optionController.setOpponentsSize(this.opponentsSizeView.getValueFromSlider());
  }
}
