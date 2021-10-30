package petrangola.views.mediator;

import petrangola.models.game.Game;
import petrangola.models.player.Player;

public interface GameMediator extends UpdatableMediator {
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