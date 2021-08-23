package main.java.petrangola.views.game;


import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.game.GameControllerImpl;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.controllers.player.PlayerControllerImpl;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.ViewNodeFactory;
import main.java.petrangola.views.ViewNodeFactoryImpl;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsExchangedImpl;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.cards.CardsViewFactoryImpl;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.components.layout.LayoutBuilderImpl;
import main.java.petrangola.views.mediator.Mediator;
import main.java.petrangola.views.mediator.GameObjectViewMediator;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class GameViewImpl extends AbstractViewFX implements GameView {
  private final Game game = new GameImpl();
  private final CardsExchanged cardsExchanged = new CardsExchangedImpl();
  private final PlayerController playerController = new PlayerControllerImpl();
  private final GameController gameController = new GameControllerImpl(game);
  private final ViewNodeFactory viewNodeFactory = new ViewNodeFactoryImpl(game, playerController, cardsExchanged);
  private final CardsViewFactory cardsViewFactory = new CardsViewFactoryImpl();
  private final LayoutBuilder layoutBuilder;
  
  private List<PlayerDetail> playersDetails = new ArrayList<>();
  private Mediator gameObjectViewMediator;
  private boolean areCardsDealt = false;
  private boolean areComponentsRegistered = false;
  private boolean arePlayersDetailsListenerAdded = false;
  
  public GameViewImpl(Stage stage, Option option) {
    super(stage, new HBox());
    
    addListenerToModel(game);
    
    getLayout().setStyle("-fx-background-image: url('" + Background.GAME.getPath() + "');" +
                               "-fx-background-repeat: no-repeat;" +
                               "-fx-background-size: cover;" +
                               "-fx-background-position: center center;");
    
    this.layoutBuilder = new LayoutBuilderImpl(getLayout(), Horizontal.values());
    
    this.layoutBuilder
          .addGroup(List.of(layoutBuilder.addPair(new FlowPane(), GameIds.SIDES_IDS.getIds())))
          .addVBox(List.of(
                layoutBuilder.addPair(new HBox(), GameIds.TOP_IDS.getIds()),
                layoutBuilder.addPair(new HBox(), GameIds.CENTRAL_IDS.getIds()),
                layoutBuilder.addPair(new HBox(), GameIds.BOTTOM_IDS.getIds())
          ))
          .addGroup(List.of(layoutBuilder.addPair(new FlowPane(), GameIds.SIDES_IDS.getIds())));
    
    this.gameController.createPlayers(option.getUsername(), option.getDifficultyLevel(), option.getOpponentsSize());
    this.gameController.createPlayerDetails();
    this.gameController.createBoard();
    this.gameController.createHighCards();
    this.gameController.setDealer();
    
  }
  
  /*
  Useful to check layouts, I need this for the rgb hex conversion
  
  private String format(double val) {
    String in = Integer.toHexString((int) Math.round(val * 255));
    return in.length() == 1 ? "0" + in : in;
  }
  
  public String toHexString(Color value) {
    return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()));
  }
  */
  
  @Override
  public void showWinner() {
  
  }
  
  @Override
  public void showCurrentPlayerName() {
    final String username = this.playersDetails
                                  .stream()
                                  .filter(playerDetail -> playerDetail.getTurnNumber() == this.game.getCurrentTurnNumber())
                                  .map(PlayerDetail::getPlayer)
                                  .map(Player::getUsername)
                                  .findFirst()
                                  .get();
    
    // TODO: finish
  }
  
  @Override
  public void showKnocks() {
  
  }
  
  @Override
  public void showRound() {
  
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (this.game.getPlayerDetails() != null && !this.arePlayersDetailsListenerAdded) {
      this.arePlayersDetailsListenerAdded = true;
      this.game.getPlayerDetails().forEach(this::addListenerToModel);
    }
    
    if (this.game.getCards() != null) {
      this.game.getCards().forEach(cards -> {
        if (cards.getCombination().getChosenCards().size() > 0) {
          this.cardsExchanged.addCards(cards);
        }
      });
  
      if (!this.areComponentsRegistered && this.playersDetails.size() > 0) {
        this.areComponentsRegistered = true;
        this.gameObjectViewMediator = new GameObjectViewMediator( viewNodeFactory, cardsViewFactory, this.game.getCards(), this.playersDetails);
        this.gameObjectViewMediator.register(getLayout());
      }
    }
    
    if (this.game.getDealer() != null && !this.areCardsDealt && this.game.getPlayerDetails().size() == this.game.getPlayers().size()) {
      this.areCardsDealt = true;
      this.game.setCards(this.game.getDealer().dealCards(this.game.getPlayerDetails(), this.game.getBoard()));
    }
    
    if (this.game.getPlayerDetails() != null && this.game.getPlayerDetails().size() == this.game.getPlayers().size()) {
      this.playersDetails = this.game.getPlayerDetails();
    }
  }
}
