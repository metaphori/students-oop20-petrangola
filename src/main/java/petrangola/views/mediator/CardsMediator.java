package main.java.petrangola.views.mediator;

import main.java.petrangola.views.player.CurrentPlayer;

public interface CardsMediator extends Mediator {
  void onUpdatedCombination();
  
  void showBoardCards();
  
  void showNPCCards();
  
  void showUserCards();
  
  void setCurrentPlayerCards(CurrentPlayer currentPlayer);
  
  void toggleUserButton(CurrentPlayer currentPlayer);
}
