package main.java.petrangola.views.components.slider;

import main.java.petrangola.views.option.commands.OpponentsSizeCommand;

public class OpponentSizeSlider extends AbstractSliderFX implements SimpleSlider<Integer> {
  private final OpponentsSizeCommand command = new OpponentsSizeCommand(this);
  private int value = 1;
  
  public OpponentSizeSlider() {
    super(1,12,1);
    
    this.setMinWidth(320);
    this.setWidth(480);
    this.setMaxWidth(600);
    this.setStyle("-fx-font-size: 14pt;");
    
    setListeners();
  }
  
  @Override
  public Integer getValueFromSlider() {
    return this.value;
  }
  
  @Override
  public void setValueFromSlider(Integer value) {
    this.value = value;
  }
  
  @Override
  public void setListeners() {
    this.valueProperty().addListener((observable, oldValue, newValue) -> {
      setValue(newValue.intValue());
      this.command.execute();
    });
  }
}
