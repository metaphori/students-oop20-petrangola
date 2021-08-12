package main.java.petrangola.views.components.slider;

import javafx.util.StringConverter;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.option.commands.DifficultyCommand;

public class DifficultySlider extends AbstractSliderFX implements SimpleSlider<DifficultyLevel> {
  private final DifficultyCommand command = new DifficultyCommand(this);
  private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
  
  public DifficultySlider() {
    super(0, 2, 0);
  
    this.setMinWidth(320);
    this.setWidth(480);
    this.setMaxWidth(600);
    this.setStyle("-fx-font-size: 15pt;");
  
    setLabels();
    setListeners();
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
    this.valueProperty().addListener((observableValue, number, t1) -> {
      setValueFromSlider(DifficultyLevel.valueOf(getStringFromDouble(t1.doubleValue()).toUpperCase()));
      this.command.execute();
    });
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
  
  private void setLabels() {
    this.setLabelFormatter(new StringConverter<>() {
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
}
