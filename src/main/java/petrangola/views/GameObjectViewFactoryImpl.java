package petrangola.views;

import javafx.scene.layout.Pane;
import petrangola.controllers.player.DealerController;
import petrangola.controllers.player.PlayerController;
import petrangola.models.game.Game;
import petrangola.models.player.PlayerDetail;
import petrangola.views.board.BoardView;
import petrangola.views.board.BoardViewImpl;
import petrangola.views.player.*;

import java.util.Objects;

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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GameObjectViewFactoryImpl)) return false;
    GameObjectViewFactoryImpl that = (GameObjectViewFactoryImpl) o;
    return getGame().equals(that.getGame()) && getPlayerController().equals(that.getPlayerController()) && getDealerController().equals(that.getDealerController()) && getLayout().equals(that.getLayout());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getGame(), getPlayerController(), getDealerController(), getLayout());
  }
}
