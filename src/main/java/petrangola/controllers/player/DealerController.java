package petrangola.controllers.player;

import petrangola.models.board.Board;
import petrangola.models.cards.Cards;
import petrangola.models.player.Dealer;
import petrangola.models.player.PlayerDetail;

import java.util.List;

public interface DealerController extends PlayerController {
  /**
   *
   */
  void dealCards(List<PlayerDetail> playersDetails, Board board, String classA);
  
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
