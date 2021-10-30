package petrangola.views.mediator;

import javafx.scene.layout.Pane;
import petrangola.controllers.game.GameController;
import petrangola.models.game.Game;
import petrangola.models.player.Player;
import petrangola.views.components.layout.LayoutBuilder;
import petrangola.views.player.CurrentPlayer;
import petrangola.views.player.GameObjectView;

import java.util.List;

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
   * @param layout
   */
  void hideDealerView(Pane layout);
  
  /**
   * @param layout
   */
  void showDealerView(Pane layout);
  
  /**
   * @param gameController
   */
  void setGameController(GameController gameController);
  
  /**
   * @param layoutBuilder
   */
  void setLayoutBuilder(LayoutBuilder layoutBuilder);
  
  /**
   * @param currentPlayer
   */
  void npcExchangeCards(Player currentPlayer);
  
  /**
   * @return
   */
  List<GameObjectView> getViewList();
  
  /**
   * @return
   */
  Pane getLayout();
  
  /**
   * @param game
   * @param oldDealer
   * @param newDealer
   */
  void updatePlayerViews(Game game, Player oldDealer, Player newDealer);
}
