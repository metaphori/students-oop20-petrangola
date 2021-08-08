package main.java.petrangola.models.player;

import java.util.List;
import main.java.petrangola.models.cards.Cards;

public interface Dealer extends Player {
  /**
   *
   * @param players
   */
  List<Cards> dealCards(List<Player> players);
  
}
