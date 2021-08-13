package main.java.petrangola.views.option;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.controllers.option.OptionControllerImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.option.OptionImpl;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.components.button.SimpleButton;
import main.java.petrangola.views.components.slider.DifficultySlider;
import main.java.petrangola.views.components.slider.OpponentSizeSlider;
import main.java.petrangola.views.components.slider.SimpleSlider;
import main.java.petrangola.views.components.textView.SimpleTextView;
import main.java.petrangola.views.components.textView.UsernameTextView;

public class OptionViewImpl extends AbstractViewFX implements OptionView {
  private final Option model = new OptionImpl();
  private final OptionController optionController = new OptionControllerImpl(model);
  
  private final SimpleSlider<DifficultyLevel> difficultySlider = new DifficultySlider(optionController);
  private final SimpleSlider<Integer> opponentSizeSlider = new OpponentSizeSlider(optionController);
  private final SimpleTextView<String> userTextView = new UsernameTextView(optionController);
  private final SimpleButton playButton =  new PlayButton(optionController);
  
  private int opponentsSize;
  private DifficultyLevel difficultyLevel;
  private String username;
  
  public OptionViewImpl(Stage stage) {
    super(stage, new VBox(24));
    
    final VBox layout = (VBox) getLayout();
    
    loadChildren(OptionViewImpl.class.getDeclaredFields(), SimpleTextView.class, SimpleSlider.class, SimpleButton.class);
    
    layout.setStyle("-fx-background-image: url('"+ Background.MENU_2.getPath()+"');"+
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
    addListenerToModel(model);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Arrays.stream(OptionViewImpl.class.getDeclaredFields()).forEach(field -> {
      if (field.getName().equals(evt.getPropertyName())) {
        try {
          field.set(this, evt.getNewValue());
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });
  
    
    if (checkDifficultyLevel() && checkUsername()) {
      this.playButton.setData(this.username, this.difficultyLevel, this.opponentsSize);
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
