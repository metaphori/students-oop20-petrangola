package main.java.petrangola.views.game;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.java.petrangola.views.components.text.TextViewImpl;

public class WinnerViewImpl extends TextViewImpl implements WinnerView {
  private static final String TEXT = "The winner of this round is : ";
  
  public WinnerViewImpl(Text component) {
    super(component);
    
    this.get().setTextAlignment(TextAlignment.CENTER);
    this.get().setFill(Paint.valueOf("white"));
    this.get().setFont(Font.font("Arial Black", FontWeight.BOLD, 32));
  }
  
  @Override
  public void setText(String text) {
    super.setText(TEXT.concat(text));
  }
}
