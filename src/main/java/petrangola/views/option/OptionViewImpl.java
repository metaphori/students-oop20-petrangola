package petrangola.views.option;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import petrangola.controllers.Controller;
import petrangola.controllers.option.OptionController;
import petrangola.models.option.Option;
import petrangola.models.option.OptionImpl;
import petrangola.utlis.Background;
import petrangola.views.AbstractViewFX;
import petrangola.views.ViewFactory;
import petrangola.views.components.AbstractComponentFX;
import petrangola.views.components.button.AbstractButtonFX;
import petrangola.views.components.slider.DifficultySlider;
import petrangola.views.components.slider.OpponentSizeSlider;
import petrangola.views.components.textFieldView.UsernameTextFieldView;
import petrangola.views.option.buttons.PlayButton;

import java.beans.PropertyChangeEvent;

public class OptionViewImpl extends AbstractViewFX implements OptionView {
  private final Option option = new OptionImpl();
  private AbstractButtonFX playButton;
  
  private OptionController optionController;
  
  public OptionViewImpl(Stage stage) {
    super(stage, new VBox(24));
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
  
  @Override
  public void setController(Controller optionController) {
    this.optionController = (OptionController) optionController;
  }
  
  @Override
  public void initView(ViewFactory viewFactory) {
    this.optionController.setModel(this.option);
    this.optionController.setViewFactory(viewFactory);
    
    final VBox layout = (VBox) getLayout();
    
    layout.setStyle("-fx-background-image: url('" + Background.MENU_2.getPath() + "');" +
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
    this.playButton = new PlayButton(this.optionController);
    
    final AbstractComponentFX<Slider> difficultySlider = new DifficultySlider(this.optionController);
    final AbstractComponentFX<Slider> opponentSizeSlider = new OpponentSizeSlider(this.optionController);
    final AbstractComponentFX<TextField> userTextView = new UsernameTextFieldView(this.optionController);
    
    this.getLayoutBuilder()
          .addNode(difficultySlider.get())
          .addNode(opponentSizeSlider.get())
          .addNode(userTextView.get())
          .addNode(playButton.get());
    
    this.addListenerToModel(option);
  }
}
