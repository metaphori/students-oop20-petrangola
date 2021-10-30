package petrangola.views.action;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import petrangola.controllers.Controller;
import petrangola.controllers.action.ActionController;
import petrangola.utlis.Background;
import petrangola.views.AbstractViewFX;
import petrangola.views.ViewFactory;
import petrangola.views.action.button.QuitButton;
import petrangola.views.action.button.StartButton;
import petrangola.views.components.AbstractComponentFX;

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
