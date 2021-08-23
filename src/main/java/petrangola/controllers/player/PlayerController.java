package main.java.petrangola.controllers.player;

import main.java.petrangola.controllers.Controller;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;

public interface PlayerController extends Controller {
  /**
   * @param player
   * @param boardCards
   * @param ownCards
   */
  void exchangeCards(final Player player, final Cards boardCards, final Cards ownCards);
  
  /**
   * @param game
   */
  void knock(final Game game);
  
  /**
   * @param playerDetail
   */
  void looseLife(final PlayerDetail playerDetail);
  
  /**
   * @param playerDetail
   * @return
   */
  boolean checkIfStillAlive(final PlayerDetail playerDetail);
  
}
