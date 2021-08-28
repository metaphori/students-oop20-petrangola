package main.java.petrangola.views.player;

import javafx.scene.text.Text;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.text.TextViewImpl;

public class UsernameViewImpl extends TextViewImpl implements UsernameView {
  private final Player player;
  
  public UsernameViewImpl(final Text component, final Player player) {
    super(component);
    this.player = player;
    super.setText(player.getUsername());
  }
  
  @Override
  public Player getPlayer() {
    return this.player;
  }
}
