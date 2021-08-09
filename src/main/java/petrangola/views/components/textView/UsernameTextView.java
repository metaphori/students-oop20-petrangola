package main.java.petrangola.views.components.textView;

import javafx.scene.control.TextField;

public class UsernameTextView extends TextField implements SimpleTextView<String> {
  private String text;
  
  public UsernameTextView() {
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
    this.textProperty().addListener((observableValue, s, t1) -> setValue(t1));
  }
}
