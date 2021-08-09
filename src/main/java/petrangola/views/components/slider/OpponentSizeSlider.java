package main.java.petrangola.views.components.slider;

public class OpponentSizeSlider extends AbstractSliderFX implements SimpleSlider<Integer> {
  private int value;
  
  public OpponentSizeSlider() {
    super(1,12,1);
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
    this.valueProperty().addListener((observable, oldValue, newValue) -> setValue(value));
  }
}
