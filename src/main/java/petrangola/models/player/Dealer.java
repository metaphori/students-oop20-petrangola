package main.java.petrangola.models.player;

import java.util.List;

import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;

public interface Dealer extends Player {
  /**
   *
   * @param playerDetails
   * @param board
   */
  List<Cards> dealCards(List<PlayerDetail> playerDetails, Board board);
}
