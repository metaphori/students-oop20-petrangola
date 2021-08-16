package main.java.petrangola.views.option;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.controllers.option.OptionControllerImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.option.OptionImpl;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.components.AbstractComponentFX;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.slider.DifficultySlider;
import main.java.petrangola.views.components.slider.OpponentSizeSlider;
import main.java.petrangola.views.components.textView.UsernameTextView;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class OptionViewImpl extends AbstractViewFX implements OptionView {
  private final Option model = new OptionImpl();
  private final OptionController optionController = new OptionControllerImpl(model);
  
  private final AbstractComponentFX<Slider> difficultySlider = new DifficultySlider(optionController);
  private final AbstractComponentFX<Slider> opponentSizeSlider = new OpponentSizeSlider(optionController);
  private final AbstractComponentFX<TextField> userTextView = new UsernameTextView(optionController);
  private final AbstractButtonFX playButton = new PlayButton(optionController);
  
  private int opponentsSize = this.model.getOpponentsSize();
  private DifficultyLevel difficultyLevel = this.model.getDifficultyLevel();
  private String username;
  
  public OptionViewImpl(Stage stage) {
    super(stage, new VBox(24));
    
    final VBox layout = (VBox) getLayout();
    
    loadChildren(OptionViewImpl.class.getDeclaredFields());
    
    layout.setStyle("-fx-background-image: url('" + Background.MENU_2.getPath() + "');" +
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
    addListenerToModel(model);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    propertyChange(evt, this);
    
    if (checkDifficultyLevel() && checkUsername()) {
      Option option = new OptionImpl();
      
      option.setUsername(this.username);
      option.setDifficultyLevel(this.difficultyLevel);
      option.setOpponentsSize(this.opponentsSize);
      
      this.playButton.setData(option);
      this.playButton.setDisable(false);
    }
  }
  
  private boolean checkUsername() {
    if (this.username == null) {
      return false;
    }
    
    return !this.username.trim().isBlank() || this.username.trim().length() > 2;
  }
  
  private boolean checkDifficultyLevel() {
    return this.difficultyLevel != null;
  }
}
