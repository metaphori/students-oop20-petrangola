package main.java.petrangola.views.components.slider;

import javafx.util.StringConverter;
import main.java.petrangola.utlis.DifficultyLevel;

public class DifficultySlider extends AbstractSliderFX implements SimpleSlider<DifficultyLevel> {
  private DifficultyLevel difficultyLevel;
  
  public DifficultySlider() {
    super(0,2,0);
    this.minWidth(480);
    
    setLabels();
    setListeners();
  }
  
  private void setLabels() {
    this.setLabelFormatter(new StringConverter<Double>() {
      @Override
      public String toString(Double aDouble) {
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
  
      @Override
      public Double fromString(String text) {
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
    });
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
    this.valueProperty().addListener((observableValue, number, t1) ->
      setValueFromSlider(DifficultyLevel.valueOf(t1.toString()))
    );
  }
}
