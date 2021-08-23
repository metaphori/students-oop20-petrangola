package main.java.petrangola.views.cards;

import main.java.petrangola.dto.DTO;
import main.java.petrangola.models.cards.Cards;

import java.util.Optional;

public interface CardsExchanged extends DTO {
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
