package petrangola.controllers.player;

import petrangola.controllers.Controller;
import petrangola.models.cards.Cards;
import petrangola.models.game.Game;
import petrangola.models.player.Player;
import petrangola.models.player.PlayerDetail;

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
