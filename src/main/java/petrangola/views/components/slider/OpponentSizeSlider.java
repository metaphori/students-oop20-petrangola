package petrangola.views.components.slider;

import petrangola.controllers.option.OptionController;
import petrangola.views.option.commands.OpponentsSizeCommand;

import java.util.Objects;

public class OpponentSizeSlider extends AbstractSliderFX<Integer> {
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OpponentSizeSlider)) return false;
    OpponentSizeSlider that = (OpponentSizeSlider) o;
    return value == that.value && command.equals(that.command);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(command, value);
  }
}
