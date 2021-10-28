package main.java.petrangola.views.action;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.Controller;
import main.java.petrangola.controllers.action.ActionController;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.action.button.QuitButton;
import main.java.petrangola.views.action.button.StartButton;
import main.java.petrangola.views.components.AbstractComponentFX;

import java.beans.PropertyChangeEvent;

public class ActionViewImpl extends AbstractViewFX implements ActionView {
  private ActionController actionController;
  
  public ActionViewImpl(Stage stage) {
    super(stage, new VBox(8));
  }
  
  @Override
  public void initView(ViewFactory viewFactory) {
    final VBox layout = (VBox) getLayout();
    
    layout.setStyle("-fx-background-image: url('" + Background.MENU.getPath() + "');" +
                          "-fx-background-repeat: no-repeat;" +
                          "-fx-background-size: cover;" +
                          "-fx-background-position: center center;");
    
    layout.setPadding(new Insets(24));
    layout.setAlignment(Pos.CENTER);
    
    this.actionController.setViewFactory(viewFactory);
    
    final AbstractComponentFX<Button> startButton = new StartButton(actionController);
    final AbstractComponentFX<Button> quitButton = new QuitButton(actionController);
    
    getLayoutBuilder().addNode(startButton.get()).addNode(quitButton.get());
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    //
  }
  
  @Override
  public void setController(Controller actionController) {
    this.actionController = (ActionController) actionController;
  }
}
