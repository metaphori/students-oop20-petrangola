package main.java.petrangola.views.option;

import java.beans.PropertyChangeEvent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.controllers.option.OptionControllerImpl;
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
  private final SimpleSlider<DifficultyLevel> difficultySlider = new DifficultySlider();
  private final SimpleSlider<Integer> opponentSizeSlider = new OpponentSizeSlider();
  private final SimpleTextView<String> userTextView = new UsernameTextView();
  private final SimpleButton playButton = new PlayButton();
  
  private final OptionController optionController = new OptionControllerImpl();
  
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
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
    
    }
  }
}
