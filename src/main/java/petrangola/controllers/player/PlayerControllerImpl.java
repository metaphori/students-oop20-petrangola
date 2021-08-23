package main.java.petrangola.controllers.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;

public class PlayerControllerImpl implements PlayerController {
  
  
  @Override
  public void exchangeCards(final Player player, final Cards boardCards, final Cards ownCards) {
    player.exchange(boardCards, ownCards);
  }
  
  @Override
  public void knock(final Game game) {
    game.setKnockerCount(game.getKnockerCount() + 1);
  }
  
  @Override
  public void looseLife(final PlayerDetail playerDetail) {
    playerDetail.takeLife();
  }
  
  @Override
  public boolean checkIfStillAlive(final PlayerDetail playerDetail) {
    return playerDetail.getPlayerLives() > 0;
  }
}
