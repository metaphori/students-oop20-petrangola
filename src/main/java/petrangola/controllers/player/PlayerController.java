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
   * @param player
   */
  void knock(final Game game, Player player);
  
  /**
   * @param playerDetail
   * @param isTaking
   */
  void lifeHandler(final PlayerDetail playerDetail, final boolean isTaking);
  
  /**
   * @param playerDetail
   * @return
   */
  boolean checkIfStillAlive(final PlayerDetail playerDetail);
  
}
