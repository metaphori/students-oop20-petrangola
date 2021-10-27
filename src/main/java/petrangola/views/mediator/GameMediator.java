package main.java.petrangola.views.mediator;

import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;

public interface GameMediator extends Mediator, UpdatableMediator {
  /**
   * @param game
   */
  void onDealer(Game game);
  
  /**
   * @param game
   */
  void onRound(Game game);
  
  /**
   * @param game
   * @param player
   */
  void onCurrentTurnNumber(Game game, Player player);
  
  /**
   * @param game
   */
  void onKnockerCount(Game game);
  
  /***
   *
   * @param winnerName
   */
  void onWinner(String winnerName);
  
  /**
   * @param game
   */
  void onBoard(Game game);
  
  /**
   * @param highCardMediator
   */
  void setHighCardMediator(HighCardMediator highCardMediator);
  
  /**
   * @return
   */
  CardsMediator getCardsMediator();
  
  /**
   * @param cardsMediator
   */
  void setCardsMediator(CardsMediator cardsMediator);
}