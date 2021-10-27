package main.java.petrangola.views.action;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.action.ActionController;
import main.java.petrangola.controllers.action.ActionControllerImpl;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.action.button.QuitButton;
import main.java.petrangola.views.action.button.StartButton;
import main.java.petrangola.views.components.AbstractComponentFX;

import java.beans.PropertyChangeEvent;

public class ActionViewImpl extends AbstractViewFX implements ActionView {
  public ActionViewImpl(Stage stage) {
    super(stage, new VBox(8));
    
    final VBox layout = (VBox) getLayout();
    
    layout.setStyle("-fx-background-image: url('" + Background.MENU.getPath() + "');" +
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
    final ActionController actionController = new ActionControllerImpl();
    final AbstractComponentFX<Button> startButton = new StartButton(actionController);
    final AbstractComponentFX<Button> quitButton = new QuitButton(actionController);
    
    getLayoutBuilder()
          .addNode(startButton.get())
          .addNode(quitButton.get());
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    //
  }
}
