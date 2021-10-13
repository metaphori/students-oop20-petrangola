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
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.components.AbstractComponentFX;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.slider.DifficultySlider;
import main.java.petrangola.views.components.slider.OpponentSizeSlider;
import main.java.petrangola.views.components.textFieldView.UsernameTextFieldView;
import main.java.petrangola.views.option.buttons.PlayButton;

import java.beans.PropertyChangeEvent;

public class OptionViewImpl extends AbstractViewFX implements OptionView {
  private final Option option = new OptionImpl();
  private final OptionController optionController = new OptionControllerImpl(option);
  private final AbstractButtonFX playButton = new PlayButton(optionController);
  
  public OptionViewImpl(Stage stage) {
    super(stage, new VBox(24), Vertical.values());
    
    final VBox layout = (VBox) getLayout();
    
    layout.setStyle("-fx-background-image: url('" + Background.MENU_2.getPath() + "');" +
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
  
    final AbstractComponentFX<Slider> difficultySlider = new DifficultySlider(optionController);
    final AbstractComponentFX<Slider> opponentSizeSlider = new OpponentSizeSlider(optionController);
    final AbstractComponentFX<TextField> userTextView = new UsernameTextFieldView(optionController);
    
    getLayoutBuilder()
          .addNode(difficultySlider.get())
          .addNode(opponentSizeSlider.get())
          .addNode(userTextView.get())
          .addNode(playButton.get());
    
    addListenerToModel(option);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (checkDifficultyLevel() && checkUsername()) {
      this.playButton.setData(this.option);
      this.playButton.setDisable(false);
    }
  }
  
  private boolean checkUsername() {
    if (this.option.getUsername() == null) {
      return false;
    }
    
    return !this.option.getUsername().trim().isBlank() || this.option.getUsername().trim().length() > 2;
  }
  
  private boolean checkDifficultyLevel() {
    return this.option.getDifficultyLevel() != null;
  }
}
