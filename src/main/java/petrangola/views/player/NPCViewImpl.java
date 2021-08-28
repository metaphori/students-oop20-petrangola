package main.java.petrangola.views.player;

import javafx.scene.Group;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsView;

public class NPCViewImpl extends AbstractPlayerViewImpl implements NPCView {
  public NPCViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    super(playerController, game, playerDetail, cardsView);
  }
  
}
