package petrangola.models.player;

import java.util.List;
import petrangola.models.cards.Cards;

public interface Dealer extends Player {
  /**
   *
   * @param players
   */
  List<Cards> dealCards(List<Player> players);
  
}
