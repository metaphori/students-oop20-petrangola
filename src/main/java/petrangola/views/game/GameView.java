package main.java.petrangola.views.game;

import main.java.petrangola.views.View;

public interface GameView extends View {
  /**
   *
   */
  void showWinner();
  
  /**
   *
   */
  void showCurrentPlayerName();
  
  /**
   *
   */
  void showKnocks();
  
  /**
   *
   */
  void showRound();
}
