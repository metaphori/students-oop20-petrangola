package main.java.petrangola.controllers.player;

import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.PlayerDetail;

import java.util.List;

public class DealerControllerImpl extends PlayerControllerImpl implements DealerController {
  private Dealer dealer;
  
  public DealerControllerImpl() {
  }
  
  @Override
  public void dealCards(List<PlayerDetail> playersDetails, Board board, String classA) {
    this.dealer.dealCards(playersDetails, board);
  }
  
  @Override
  public void cherryPickingCombination(Cards boardCard, Cards ownCards) {
    this.dealer.firstExchange(boardCard, ownCards);
  }
  
  public Dealer getDealer() {
    return dealer;
  }
  
  public void setDealer(Dealer dealer) {
    this.dealer = dealer;
  }
}
