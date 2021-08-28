package main.java.petrangola.views.components.text;

import javafx.scene.text.Text;
import main.java.petrangola.views.components.AbstractComponentFX;

public abstract class TextViewImpl extends AbstractComponentFX<Text> implements TextViewFX {
  public TextViewImpl(Text component) {
    super(component);
    this.get().setVisible(false);
  }
  
  @Override
  public void setListeners() {
    this.get().setOnMouseEntered(mouseEvent -> {
      this.get().setStyle("-fx-background-color: linear-gradient(focus-angle 45deg, #85FFBD 0%, #FFFB7D 100%)");
    });
  
    this.get().setOnMouseExited(mouseEvent -> {
      this.get().setStyle("");
    });
  }
  
  @Override
  public String getText() {
    return this.get().getText();
  }
  
  @Override
  public void setText(String text) {
    this.get().setText(text);
  }
  
  @Override
  public void show() {
    this.get().setVisible(true);
  }
  
  @Override
  public void hide() {
    this.get().setVisible(false);
  }
}
