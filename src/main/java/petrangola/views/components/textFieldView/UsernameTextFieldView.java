package main.java.petrangola.views.components.textFieldView;

import javafx.scene.control.TextField;
import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.views.components.AbstractComponentFX;
import main.java.petrangola.views.option.commands.UsernameCommand;

public class UsernameTextFieldView extends AbstractComponentFX<TextField> implements SimpleTextFieldView<String> {
  private final UsernameCommand command;
  private String text;
  
  public UsernameTextFieldView(final OptionController optionController) {
    super(new TextField());
    super.get().setMinWidth(320);
   // super.get().setWidth(380);
    super.get().setMaxWidth(480);
    super.get().setStyle("-fx-font-size: 14pt;");
    setListeners();
    
    this.command = new UsernameCommand(this, optionController);
  }
  
  @Override
  public String getValue() {
    return this.text;
  }
  
  @Override
  public void setValue(String value) {
    this.text = value;
  }
  
  @Override
  public void setListeners() {
    super.get().textProperty().addListener((observableValue, s, t1) -> {
      setValue(t1);
      this.command.execute();
    });
  }
}
