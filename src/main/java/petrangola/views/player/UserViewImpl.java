package main.java.petrangola.views.player;

import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;

import java.util.List;

public class UserViewImpl extends AbstractPlayerViewImpl implements UserView {
  public UserViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final CardsView<List<CardView>> cardsView, CardsExchanged cardsExchanged) {
    super(playerController, game, playerDetail, cardsView, cardsExchanged);
  }
}
