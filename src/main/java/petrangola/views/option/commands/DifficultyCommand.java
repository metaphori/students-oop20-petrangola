package main.java.petrangola.views.option.commands;

import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.components.slider.SimpleSlider;

public class DifficultyCommand extends AbstractOptionCommand {
  private final SimpleSlider<DifficultyLevel> difficultyLevelView;
  
  public DifficultyCommand(final SimpleSlider<DifficultyLevel> difficultyLevelView) {
    this.difficultyLevelView = difficultyLevelView;
  }
  
  @Override
  public void execute() {
    this.optionController.setDifficulty(this.difficultyLevelView.getValueFromSlider());
  }
}
