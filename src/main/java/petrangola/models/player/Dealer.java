package main.java.petrangola.models.player;

import java.util.List;

import main.java.petrangola.models.board.Board;

public interface Dealer extends Player {
  /**
   *
   * @param playerDetails
   * @param board
   */
  void dealCards(List<PlayerDetail> playerDetails, Board board);
}
