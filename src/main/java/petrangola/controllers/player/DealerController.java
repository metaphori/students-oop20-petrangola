package main.java.petrangola.controllers.player;

import main.java.petrangola.controllers.Controller;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.PlayerDetail;

import java.util.List;

public interface DealerController extends PlayerController {
  /**
   *
   */
  void dealCards(List<PlayerDetail> playerDetails, Board board);
  
  /**
   *
   */
  void cherryPickingCombination(Cards boardCard, Cards ownCards);
  
  
  /**
   *
   * @param dealer
   */
  void setDealer(Dealer dealer);
  
  /**
   *
   * @return
   */
  Dealer getDealer();
  
}
