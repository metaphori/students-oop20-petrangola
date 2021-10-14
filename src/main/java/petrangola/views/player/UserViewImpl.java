package main.java.petrangola.views.player;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsExchanged;

public class UserViewImpl extends AbstractPlayerViewImpl implements UserView {
  private CardsExchanged cardsExchanged;
  
  public UserViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(playerController, game, playerDetail, layout);
  }
  
  @Override
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  @Override
  public CardsExchanged getCardsExchanged() {
    return this.cardsExchanged;
  }
}
