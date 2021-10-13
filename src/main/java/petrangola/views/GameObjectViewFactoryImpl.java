package main.java.petrangola.views;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.board.BoardViewImpl;
import main.java.petrangola.views.player.*;

public class GameObjectViewFactoryImpl implements GameObjectViewFactory {
  private final Game game;
  private final PlayerController playerController;
  private final DealerController dealerController;
  private final Pane layout;
  
  public GameObjectViewFactoryImpl(final Game game, final PlayerController playerController, final DealerController dealerController, final Pane layout) {
    this.game = game;
    this.playerController = playerController;
    this.dealerController = dealerController;
    this.layout = layout;
  }
  
  @Override
  public NPCView createNPCView(final PlayerDetail playerDetail) {
    return new NPCViewImpl(getPlayerController(), getGame(), playerDetail, getLayout());
  }
  
  @Override
  public UserView createUserView(final PlayerDetail playerDetail) {
    return new UserViewImpl(getPlayerController(), getGame(), playerDetail, getLayout());
  }
  
  @Override
  public DealerView createDealerView(final PlayerDetail playerDetail) {
    return new DealerViewImpl(getDealerController(), getGame(), playerDetail, getLayout());
  }
  
  @Override
  public BoardView createBoardView() {
    return new BoardViewImpl();
  }
  
  private PlayerController getPlayerController() {
    return this.playerController;
  }
  
  private DealerController getDealerController() {
    return this.dealerController;
  }
  
  private Game getGame() {
    return this.game;
  }
  
  private Pane getLayout() {
    return this.layout;
  }
}
