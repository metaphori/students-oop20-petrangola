package petrangola.views.cards;

import petrangola.dto.DTO;
import petrangola.models.cards.Cards;

import java.util.Optional;

public interface CardsExchanged extends DTO, Exchangeable {
  /**
   *
   * @param cards
   */
  void addCards(Cards cards);
  
  /**
   *
   * @return
   */
  Optional<Cards> getPlayerCards();
  
  /**
   *
   * @return
   */
  Optional<Cards> getBoardCards();
  
}
