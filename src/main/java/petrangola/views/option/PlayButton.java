package main.java.petrangola.views.option;

import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.button.SimpleButton;

public class PlayButton extends AbstractButtonFX implements SimpleButton {
  private final static String PLAY = "Play";
  
  public PlayButton() {
    super(PLAY);
  }
  
  @Override
  public void handleStyle() {
    this.setMinWidth(ViewConstants.WIDTH.getLength() * 0.3);
  }
  
  @Override
  public void setListeners() {
  
  }
}