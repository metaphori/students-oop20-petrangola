package main.java.petrangola.views.animation.player;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.animation.Animation;

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
