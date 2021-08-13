package main.java.petrangola.views.components.slider;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.option.commands.OpponentsSizeCommand;

public class OpponentSizeSlider extends AbstractSliderFX implements SimpleSlider<Integer> {
  private final OpponentsSizeCommand command;
  private int value = 1;
  
  public OpponentSizeSlider(final OptionController optionController) {
    super(1,12,1);
    
    this.setMinWidth(320);
    this.setWidth(480);
    this.setMaxWidth(600);
    this.setStyle("-fx-font-size: 14pt;");
    
    setListeners();
    
    this.command =  new OpponentsSizeCommand(this, optionController);
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
      setValueFromSlider(newValue.intValue());
      command.execute();
    });
  }
}
