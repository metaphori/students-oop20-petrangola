package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.components.slider.SimpleSlider;

import java.util.Objects;

public class OpponentsSizeCommand extends AbstractOptionCommand {
  private final SimpleSlider<Integer> opponentsSizeView;
  
  public OpponentsSizeCommand(final SimpleSlider<Integer> opponentsSizeView, final OptionController optionController) {
    super(optionController);
    this.opponentsSizeView = opponentsSizeView;
  }
  
  @Override
  public void execute() {
    this.getOptionController().setOpponentsSize(this.opponentsSizeView.getValueFromSlider());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OpponentsSizeCommand)) return false;
    OpponentsSizeCommand that = (OpponentsSizeCommand) o;
    return opponentsSizeView.equals(that.opponentsSizeView);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(opponentsSizeView);
  }
}
