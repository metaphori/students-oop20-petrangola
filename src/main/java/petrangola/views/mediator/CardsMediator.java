package main.java.petrangola.views.mediator;

import main.java.petrangola.views.player.CurrentPlayer;

public interface CardsMediator extends Mediator {
  /**
   *
   */
  void showBoardCards();
  
  /**
   *
   */
  void showNPCCards();
  
  /**
   *
   */
  void showUserCards();
  
  /**
   * @param currentPlayer
   */
  void setCurrentPlayerCards(CurrentPlayer currentPlayer);
  
  /**
   * @param currentPlayer
   */
  void toggleUserButton(CurrentPlayer currentPlayer);
}
