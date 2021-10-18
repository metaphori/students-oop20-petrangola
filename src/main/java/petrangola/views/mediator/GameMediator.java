package main.java.petrangola.views.mediator;

import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;

public interface GameMediator extends Mediator, UpdatableMediator {
  /**
   * @param dealer
   */
  void onDealer(Dealer dealer);
  
  /**
   * @param round
   */
  void onRound(int round);
  
  /**
   * @param player
   */
  void onCurrentTurnNumber(Player player);
  
  /**
   * @param game
   */
  void onKnockerCount(Game game);
  
  /**
   *
   */
  void onLastKnocker();
  
  /***
   *
   * @param winnerName
   */
  void onWinner(String winnerName);
  
  /**
   *
   * @param game
   */
  void onBoard(Game game);
  
  /**
   * @param game
   */
  void onOnlyOneRound(Game game);
  
  /**
   * @param highCardMediator
   */
  void setHighCardMediator(HighCardMediator highCardMediator);
  
  /**
   * @param cardsMediator
   */
  void setCardsMediator(CardsMediator cardsMediator);
  
  /**
   *
   * @return
   */
  CardsMediator getCardsMediator();
}