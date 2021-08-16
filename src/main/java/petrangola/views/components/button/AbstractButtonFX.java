package main.java.petrangola.views.components.button;

import javafx.scene.control.Button;
import main.java.petrangola.dto.DTO;
import main.java.petrangola.utlis.UserAction;
import main.java.petrangola.views.components.AbstractComponentFX;
import main.java.petrangola.views.components.style.StyleBuilder;
import main.java.petrangola.views.components.style.StyleBuilderImpl;

import java.util.List;

public abstract class AbstractButtonFX extends AbstractComponentFX<Button> implements SimpleButton<Button> {
  private final StyleBuilder styleBuilder = new StyleBuilderImpl();
  private DTO data;
  
  
  public AbstractButtonFX(String label) {
    super(new Button(label));
    this.handleStyle();
    this.setListeners();
  }
  
  @Override
  public void handleStyle() {
    super.get().setStyle(this.getStyleBuilder().getStyles(UserAction.NOTHING));
    
    super.get().hoverProperty().addListener((observableValue, aBoolean, t1) -> {
      if (t1) {
        super.get().setStyle(this.getStyleBuilder().getStyles(UserAction.HOVER));
      } else {
        super.get().setStyle(this.getStyleBuilder().getStyles(UserAction.NOTHING));
      }
    });
    
    super.get().setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.isConsumed()) {
        super.get().setStyle(this.getStyleBuilder().getStyles(UserAction.NOTHING));
      } else {
        super.get().setStyle(this.getStyleBuilder().getStyles(UserAction.PRESS));
      }
    });
  }
  
  @Override
  public void setDisable(boolean isDisabled) {
    super.get().setDisable(isDisabled);
  }
  
  protected StyleBuilder getStyleBuilder() {
    return this.styleBuilder;
  }
  
  protected DTO getData() {
    return this.data;
  }
  
  @Override
  public void setData(DTO data) {
    this.data = data;
  }
}
