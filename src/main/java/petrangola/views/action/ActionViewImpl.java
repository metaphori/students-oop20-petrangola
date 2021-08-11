package main.java.petrangola.views.action;

import java.beans.PropertyChangeEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.components.button.SimpleButton;

public class ActionViewImpl extends AbstractViewFX implements ActionView {
  
  public ActionViewImpl(Stage stage) {
    super(stage, new VBox(8));
  
    final VBox layout = (VBox) getLayout();
    final SimpleButton startButton = new StartButton();
    final SimpleButton quitButton = new QuitButton();
    
    
    layout.getChildren().addAll((Node) startButton, (Node) quitButton);
    
    layout.setStyle("-fx-background-image: url('"+Background.MENU.getPath()+"');"+
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;");
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
  
  
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
  
  }
}
