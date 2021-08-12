package main.java.petrangola.views.action;


import main.java.petrangola.controllers.action.ActionController;
import main.java.petrangola.utlis.ViewConstants;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.button.SimpleButton;

public class StartButton extends AbstractButtonFX implements SimpleButton {
  private final ActionController actionController;
  private final static String START = "Start";
  
  public StartButton(ActionController actionController) {
    super(START);
    
    this.actionController = actionController;
  }
  
  @Override
  public void handleStyle() {
    String normal = "-fx-padding: 8 15 15 15;\n" +
                          "      -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                          "      -fx-background-radius: 8;\n" +
                          "      -fx-background-color:\n" +
                          "      linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                          "        #9d4024,\n" +
                          "        #d86e3a,\n" +
                          "               radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                          "      -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                          "      -fx-font-weight: bold;\n" +
                          "      -fx-font-size: 1.1em;" +
                          "      -fx-text-fill: white;\n" +
                          "      -fx-text-effect: dropshadow( gaussian , #a30000 , 0,0,0,2 );";
    
    String hover = "  -fx-background-color:\n" +
                         "      linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                         "        #9d4024,\n" +
                         "        #d86e3a,\n" +
                         "               radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);";
    
    String pressed = "-fx-padding: 10 15 13 15;\n" +
                           "    -fx-background-insets: 2 0 0 0,2 0 3 0, 2 0 4 0, 2 0 5 0;";
  
  
    this.setStyle(normal);
    
    this.hoverProperty().addListener((observableValue, aBoolean, t1) -> {
      if (t1) {
        this.setStyle(this.getStyle().concat(hover));
      } else {
        this.setStyle(normal);
      }
    });
    
    this.setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.isConsumed()) {
        this.setStyle(normal);
      } else {
        this.setStyle(this.getStyle().concat(pressed));
      }
    });
  
    this.setMinWidth(ViewConstants.WIDTH.getLength() * 0.5);
    this.setWidth(ViewConstants.WIDTH.getLength() * 0.6);
    this.setMaxWidth(ViewConstants.WIDTH.getLength() * 0.7);
  }
  
  @Override
  public void setListeners() {
    this.setOnMouseClicked(mouseEvent -> this.actionController.start());
  }
}
