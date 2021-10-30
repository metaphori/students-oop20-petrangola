package petrangola.views.animation.player;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import petrangola.controllers.player.DealerController;
import petrangola.models.board.Board;
import petrangola.models.player.PlayerDetail;
import petrangola.views.ViewFX;
import petrangola.views.animation.AbstractAnimation;
import petrangola.views.game.DealerTextView;
import petrangola.views.game.DealerTextViewImpl;
import petrangola.views.game.GameStyleClass;
import petrangola.views.mediator.HighCardMediator;

import java.util.List;
import java.util.Objects;

public class DealerAnimationImpl extends AbstractAnimation implements DealerAnimation {
  private final DealerTextView dealerTextView = new DealerTextViewImpl(new Text());
  private final Pane layout;
  
  private DealerController dealerController;
  private List<PlayerDetail> playersDetails;
  private Board board;
  
  public DealerAnimationImpl(Pane layout) {
    this.layout = layout;
  }
  
  public void cleanUp() {
    this.getTimeline().getKeyFrames().clear();
  }
  
  @Override
  public EventHandler<ActionEvent> showDealerName() {
    return actionEvent -> {
      final Pane usernamePane = (Pane) getLayout().lookup(GameStyleClass.USERNAME.getAsStyleClass());
      this.dealerTextView.setCurrentDealerName(getDealerController().getDealer().getUsername());
      this.dealerTextView.show();
      
      ViewFX.addOrUpdate(usernamePane, this.dealerTextView.get());
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
    return actionEvent -> getDealerController().dealCards(this.getPlayersDetails(), this.getBoard(), this.getClass().toString());
  }
  
  private DealerController getDealerController() {
    return this.dealerController;
  }
  
  @Override
  public void setDealerController(DealerController dealerController) {
    this.dealerController = dealerController;
  }
  
  private List<PlayerDetail> getPlayersDetails() {
    return this.playersDetails;
  }
  
  @Override
  public void setPlayersDetails(List<PlayerDetail> playersDetails) {
    this.playersDetails = playersDetails;
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DealerAnimationImpl)) return false;
    if (!super.equals(o)) return false;
    DealerAnimationImpl that = (DealerAnimationImpl) o;
    return dealerTextView.equals(that.dealerTextView) && getLayout().equals(that.getLayout()) && getDealerController().equals(that.getDealerController()) && getPlayersDetails().equals(that.getPlayersDetails()) && getBoard().equals(that.getBoard());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), dealerTextView, getLayout(), getDealerController(), getPlayersDetails(), getBoard());
  }
}
