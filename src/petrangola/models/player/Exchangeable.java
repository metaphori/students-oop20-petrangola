package petrangola.models.player;

import petrangola.models.Cards;

@FunctionalInterface
public interface Exchangeable {
  /**
   * @return the new set of Cards of the entity ( Player / Board )
   */
  Cards exchange(final Cards boardCards, final Cards playerCards);
}
