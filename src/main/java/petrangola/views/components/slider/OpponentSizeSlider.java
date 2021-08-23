package main.java.petrangola.views.components.slider;

import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.option.commands.OpponentsSizeCommand;

public class OpponentSizeSlider extends AbstractSliderFX implements SimpleSlider<Integer> {
  private final OpponentsSizeCommand command;
  private int value = 1;
  
  public OpponentSizeSlider(final OptionController optionController) {
    super(1, 11, 1);
    
    super.get().setMinWidth(320);
    // super.get().setWidth(480);
    super.get().setMaxWidth(600);
    super.get().setStyle("-fx-font-size: 14pt;");
    
    setListeners();
    
    this.command = new OpponentsSizeCommand(this, optionController);
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
    super.get().valueProperty().addListener((observable, oldValue, newValue) -> {
      setValueFromSlider(newValue.intValue());
      command.execute();
    });
  }
}
