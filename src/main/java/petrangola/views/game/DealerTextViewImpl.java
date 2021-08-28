package main.java.petrangola.views.game;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.java.petrangola.views.components.text.TextViewImpl;

public class DealerTextViewImpl extends TextViewImpl implements DealerTextView {
  public DealerTextViewImpl(Text component) {
    super(component);
    
    this.get().setTextAlignment(TextAlignment.CENTER);
    this.get().setFill(Paint.valueOf("white"));
    this.get().setFont(Font.font("Arial Black", FontWeight.BOLD, 28));
  }
  
  @Override
  public void setCurrentDealerName(String dealerName) {
    super.setText(dealerName);
  }
}
