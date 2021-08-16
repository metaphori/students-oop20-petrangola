package main.java.petrangola.views.game;


import javafx.stage.Stage;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.game.GameControllerImpl;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.components.layout.LayoutBuilderImpl;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class GameViewImpl extends AbstractViewFX implements GameView {
  private final Game game = new GameImpl();
  private final GameController gameController = new GameControllerImpl(game);
  // TODO: delete this fields and only update Views ( also in OptionViewImpl )
  private List<PlayerDetail> playerDetails;
  private List<Player> players;
  private List<Cards> cards;
  private Board board;
  private Dealer dealer;
  private int round;
  private int currentTurnNumber;
  private int knockerCount;
  private String lastKnocker;
  private String winner;
  private boolean onlyOneRound = false;
  
  public GameViewImpl(Stage stage, Option option) {
    super(stage, new LayoutBuilderImpl()
                       .addHBox(8)
                       .addVBox(8, true)
                       .addHBoxForEach(8)
                       .getLayout());
    
    addListenerToModel(game);
    
    getLayout().setStyle("-fx-background-image: url('" + Background.GAME.getPath() + "');" +
                               "-fx-background-repeat: no-repeat;" +
                               "-fx-background-size: cover;" +
                               "-fx-background-position: center center;");
  
    System.out.println(option.getUsername() + "  " + option.getDifficultyLevel() + "  " + option.getOpponentsSize() + "  " );
    
    this.gameController.createPlayers(option.getUsername(), option.getDifficultyLevel(), option.getOpponentsSize());
    this.gameController.createPlayerDetails();
    this.gameController.createBoard();
    this.gameController.createHighCards();
    this.gameController.setDealer();
  }
  
  @Override
  public void showWinner() {
  
  }
  
  @Override
  public void showCurrentPlayerName() {
  
  }
  
  @Override
  public void showKnocks() {
  
  }
  
  @Override
  public void showRound() {
  
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    propertyChange(evt, this);
    
    if (this.playerDetails != null) {
      this.playerDetails.forEach(this::addListenerToModel);
    }
    
    if (this.cards != null) {
      this.cards.forEach(this::addListenerToModel);
    }
    
    if (this.dealer != null) {
      this.dealer.dealCards(this.playerDetails, this.board);
    }
  }
}
