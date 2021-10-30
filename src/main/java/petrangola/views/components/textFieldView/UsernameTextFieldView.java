package petrangola.views.components.textFieldView;

import javafx.scene.control.TextField;
import petrangola.controllers.option.OptionController;
import petrangola.views.option.commands.UsernameCommand;

import java.util.Objects;

public class UsernameTextFieldView extends AbstractTextField<String> {
  private final UsernameCommand command;
  
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
  public void setListeners() {
    super.get().textProperty().addListener((observableValue, s, t1) -> {
      setValue(t1);
      this.command.execute();
    });
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UsernameTextFieldView)) return false;
    UsernameTextFieldView that = (UsernameTextFieldView) o;
    return command.equals(that.command);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(command);
  }
}
