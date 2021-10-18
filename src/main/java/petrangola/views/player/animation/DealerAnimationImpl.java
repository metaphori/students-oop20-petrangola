package main.java.petrangola.views.player.animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.game.DealerTextView;
import main.java.petrangola.views.game.DealerTextViewImpl;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.mediator.HighCardMediator;

import java.util.List;

public class DealerAnimationImpl extends AbstractPlayerAnimation implements DealerAnimation {
  private final DealerTextView dealerTextView = new DealerTextViewImpl(new Text());
  private final Pane layout;
  
  private DealerController dealerController;
  private List<PlayerDetail> playersDetails;
  private Board board;
  
  public DealerAnimationImpl(Pane layout) {
    this.layout = layout;
  }
  
  @Override
  public EventHandler<ActionEvent> showDealerName() {
    return actionEvent -> {
      final Pane usernamePane = (Pane) getLayout().lookup(GameStyleClass.USERNAME.getAsStyleClass());
      this.dealerTextView.setCurrentDealerName(getDealerController().getDealer().getUsername());
      this.dealerTextView.show();
      
      usernamePane.getChildren().add(this.dealerTextView.get());
    };
  }
  
  @Override
  public EventHandler<ActionEvent> hideHighCards(HighCardMediator highCardMediator) {
    return actionEvent -> {
      highCardMediator.unregister(getLayout());
      this.dealerTextView.hide();
    
      final Pane pane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
      layout.getChildren().removeAll(pane.getChildren());
    };
  }
  
  @Override
  public EventHandler<ActionEvent> dealCards() {
    return actionEvent -> getDealerController().dealCards(getPlayersDetails(), getBoard());
  }
  
  @Override
  public void setPlayerDetails(List<PlayerDetail> playersDetails) {
    this.playersDetails = playersDetails;
  }
  
  private DealerController getDealerController() {
    return this.dealerController;
  }
  
  @Override
  public void setDealerController(DealerController dealerController) {
    this.dealerController = dealerController;
  }
  
  private List<PlayerDetail> getPlayersDetails() {
    return playersDetails;
  }
  
  private Board getBoard() {
    return this.board;
  }
  
  @Override
  public void setBoard(Board board) {
    this.board = board;
  }
  
  private Pane getLayout() {
    return this.layout;
  }
}
