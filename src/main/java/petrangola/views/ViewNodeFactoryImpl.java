package main.java.petrangola.views;

import javafx.scene.Group;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.board.BoardViewImpl;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.game.*;
import main.java.petrangola.views.player.*;

public class ViewNodeFactoryImpl implements ViewNodeFactory {
  private final Game game;
  private final CardsExchanged cardsExchanged;
  private final PlayerController playerController;
  
  public ViewNodeFactoryImpl(final Game game, final PlayerController playerController, CardsExchanged cardsExchanged) {
    this.game = game;
    this.cardsExchanged = cardsExchanged;
    this.playerController = playerController;
  }
  
  @Override
  public NPCView createNPCView(final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    return new NPCViewImpl(this.playerController, this.game, playerDetail, cardsView);
  }
  
  @Override
  public UserView createUserView(final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    return new UserViewImpl(this.playerController, this.game, playerDetail, cardsView, this.cardsExchanged);
  }
  
  @Override
  public DealerView createDealerView(final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    return new DealerViewImpl(this.playerController, this.game, playerDetail, cardsView);
  }
  
  @Override
  public BoardView createBoardView(CardsView<Group> cardsView) {
    return new BoardViewImpl(cardsView);
  }
  
  @Override
  public KnockView createKnockView() {
    return new KnockViewImpl(new Text());
  }
  
  @Override
  public RoundView createRoundView() {
    return new RoundViewImpl(new Text());
  }
  
  @Override
  public WinnerView createWinnerView() {
    return new WinnerViewImpl(new Text());
  }
}
