package petrangola.views.components.slider;

import javafx.util.StringConverter;
import petrangola.controllers.option.OptionController;
import petrangola.utlis.DifficultyLevel;
import petrangola.views.option.commands.DifficultyCommand;

import java.util.Objects;

public class DifficultySlider extends AbstractSliderFX<DifficultyLevel> implements SimpleSlider<DifficultyLevel> {
  private final DifficultyCommand command;
  private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
  
  public DifficultySlider(final OptionController optionController) {
    super(0, 2, 0);
    
    super.get().setMinWidth(320);
    // super.get().setWidth(480);
    super.get().setMaxWidth(600);
    super.get().setStyle("-fx-font-size: 15pt;");
    
    setLabels();
    setListeners();
    
    this.command = new DifficultyCommand(this, optionController);
  }
  
  private static String getStringFromDouble(Double aDouble) {
    String text = "";
    
    if (aDouble == 0d) {
      text = "Easy";
    } else if (aDouble == 1d) {
      text = "Intermediate";
    } else if (aDouble == 2d) {
      text = "Advanced";
    }
    
    return text;
  }
  
  private static double getDoubleFromString(String text) {
    double value = 0d;
    
    switch (text) {
      case "Easy":
        value = 0d;
        break;
      case "Intermediate":
        value = 1d;
        break;
      case "Advanced":
        value = 2d;
        break;
    }
    
    return value;
  }
  
  @Override
  public DifficultyLevel getValueFromSlider() {
    return this.difficultyLevel;
  }
  
  @Override
  public void setValueFromSlider(DifficultyLevel value) {
    this.difficultyLevel = value;
  }
  
  
  @Override
  public void setListeners() {
    super.get().valueProperty().addListener((observableValue, number, t1) -> {
      setValueFromSlider(DifficultyLevel.valueOf(getStringFromDouble(t1.doubleValue()).toUpperCase()));
      this.command.execute();
    });
  }
  
  private void setLabels() {
    super.get().setLabelFormatter(new StringConverter<>() {
      @Override
      public String toString(Double aDouble) {
        return getStringFromDouble(aDouble);
      }
      
      @Override
      public Double fromString(String text) {
        return getDoubleFromString(text);
      }
    });
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DifficultySlider)) return false;
    DifficultySlider that = (DifficultySlider) o;
    return command.equals(that.command) && difficultyLevel == that.difficultyLevel;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(command, difficultyLevel);
  }
}
