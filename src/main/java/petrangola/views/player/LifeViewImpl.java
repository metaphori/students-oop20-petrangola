package main.java.petrangola.views.player;

import javafx.scene.text.Text;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.components.text.TextViewImpl;

public class LifeViewImpl extends TextViewImpl implements LifeView {
  private static final String TEXT = "Vite: ";
  private final Player player;
  
  public LifeViewImpl(final Text component, final PlayerDetail playerDetail) {
    super(component);
    this.player = playerDetail.getPlayer();
    super.setText(TEXT.concat(String.valueOf(playerDetail.getPlayerLives())));
  }
  
  @Override
  public Player getPlayer() {
    return this.player;
  }
}
