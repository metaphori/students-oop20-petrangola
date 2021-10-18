package main.java.petrangola.views.mediator;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.components.layout.LayoutBuilder;
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
  
  /**
   *
   * @param layout
   */
  void hideDealerView(Pane layout);
  
  /**
   *
   * @param layout
   */
  void showDealerView(Pane layout);
  
  /**
   *
   * @param gameController
   */
  void setGameController(GameController gameController);
  
  /**
   *
   * @param layoutBuilder
   */
  void setLayoutBuilder(LayoutBuilder layoutBuilder);
  
  /**
   *
   * @param currentPlayer
   */
  void npcExchangeCards(Player currentPlayer);
}
