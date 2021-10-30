package petrangola.views.animation.player;

import petrangola.models.cards.Cards;
import petrangola.models.player.Player;
import petrangola.views.animation.Animation;

import java.util.List;

public interface PlayerAnimation extends Animation {
  /**
   *
   * @param player
   */
  void onFirstExchange(Player player);
  
  /**
   *
   * @param player
   * @param cardsList
   */
  void onExchange(Player player, List<Cards> cardsList);
}
