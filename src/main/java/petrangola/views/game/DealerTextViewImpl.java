package petrangola.views.game;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import petrangola.views.components.text.TextViewImpl;

public class DealerTextViewImpl extends TextViewImpl implements DealerTextView {
  public DealerTextViewImpl(Text component) {
    super(component);
    
    this.get().setTextAlignment(TextAlignment.CENTER);
    this.get().setFont(Font.font("Arial Black", FontWeight.BOLD, 28));
  }
  
  @Override
  public void setCurrentDealerName(String dealerName) {
    super.setText(dealerName);
  }
}
