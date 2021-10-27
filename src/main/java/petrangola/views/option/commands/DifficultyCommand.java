package main.java.petrangola.views.option.commands;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.components.slider.SimpleSlider;

import java.util.Objects;

public class DifficultyCommand extends AbstractOptionCommand {
  private final SimpleSlider<DifficultyLevel> difficultyLevelView;
  
  public DifficultyCommand(final SimpleSlider<DifficultyLevel> difficultyLevelView, final OptionController optionController) {
    super(optionController);
    this.difficultyLevelView = difficultyLevelView;
  }
  
  @Override
  public void execute() {
    this.getOptionController().setDifficulty(this.difficultyLevelView.getValueFromSlider());
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DifficultyCommand)) return false;
    DifficultyCommand that = (DifficultyCommand) o;
    return difficultyLevelView.equals(that.difficultyLevelView);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(difficultyLevelView);
  }
}
