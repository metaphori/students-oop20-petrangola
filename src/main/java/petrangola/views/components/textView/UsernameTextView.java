package main.java.petrangola.views.components.textView;

import javafx.scene.control.TextField;
import main.java.petrangola.views.option.commands.UsernameCommand;

public class UsernameTextView extends TextField implements SimpleTextView<String> {
  private final UsernameCommand command = new UsernameCommand(this);
  private String text;
  
  public UsernameTextView() {
    this.setMinWidth(320);
    this.setWidth(380);
    this.setMaxWidth(480);
    this.setStyle("-fx-font-size: 14pt;");
    setListeners();
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
    this.textProperty().addListener((observableValue, s, t1) -> {
      setValue(t1);
      this.command.execute();
    });
  }
}
