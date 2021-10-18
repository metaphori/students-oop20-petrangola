package main.java.petrangola.views.game;


import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.game.GameControllerImpl;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.controllers.player.DealerControllerImpl;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.controllers.player.PlayerControllerImpl;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.utlis.Background;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.views.AbstractViewFX;
import main.java.petrangola.views.GameObjectViewFactory;
import main.java.petrangola.views.GameObjectViewFactoryImpl;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.cards.CardsViewFactoryImpl;
import main.java.petrangola.views.events.EventManagerImpl;
import main.java.petrangola.views.mediator.*;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class GameViewImpl extends AbstractViewFX implements GameView {
  private final Game game = new GameImpl();
  
  private final DealerController dealerController = new DealerControllerImpl();
  private final PlayerController playerController = new PlayerControllerImpl();
  private final GameController gameController = new GameControllerImpl(game);
  
  private final GameObjectViewFactory gameObjectViewFactory = new GameObjectViewFactoryImpl(game, playerController, dealerController, getLayout());
  private final CardsViewFactory cardsViewFactory = new CardsViewFactoryImpl();
  private final MediatorsFactory mediatorsFactory = new MediatorsFactoryImpl();
  
  private GameMediator gameMediator;
  private HighCardMediator highCardsMediator;
  
  public GameViewImpl(Stage stage, Option option) {
    super(stage, new HBox(), Horizontal.values());
    
    addListenerToModel(this.game);
    
    this.registerEvents();
    this.initLayout();
    this.init(option);
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    this.mediatorsInitializer(evt.getPropertyName());
    
    if (this.gameMediator == null) {
      this.gameMediator = getMediatorsFactory().createGameMediator(getLayoutBuilder(), getGameController(), getDealerController());
    }
    
    if (evt.getPropertyName().equals("dealer")) {
      this.addListenerToModel((Dealer) evt.getNewValue());
    }
    
    if (evt.getPropertyName().equals("dealtCards")) {
      this.game.setCards((List<Cards>) evt.getNewValue());
      this.getGameMediator().update(evt.getPropertyName(), this.game);
    } else {
      this.getGameMediator().update(evt.getPropertyName(), evt.getSource());
    }
  }
  
  private void registerEvents() {
    final EventManagerImpl eventManager = new EventManagerImpl(gameController);
    eventManager.register();
  }
  
  private void init(Option option) {
    this.getGameController().createPlayers(option.getUsername(), option.getDifficultyLevel(), option.getOpponentsSize());
    this.getGameController().createPlayerDetails();
    this.getGameController().createHighCards();
    this.getGameController().createBoard();
    this.getGameController().setDealer();
  }
  
  private void initLayout() {
    this.getLayout().setStyle("-fx-background-image: url('" + Background.GAME.getPath() + "');" +
                                    "-fx-background-repeat: no-repeat;" +
                                    "-fx-background-size: cover;" +
                                    "-fx-background-position: center center;");
    
    final FlowPane leftPane = createFlowPane(Orientation.VERTICAL);
    final FlowPane rightPane = createFlowPane(Orientation.VERTICAL);
    
    leftPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    rightPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    
    this.getLayoutBuilder()
          .addSimplePane(getLayoutBuilder().addPair(leftPane, GameStyleClass.SIDES_IDS.getClasses()))
          .addVBox(List.of(
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.TOP_IDS.getClasses()),
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.CENTRAL_IDS.getClasses()),
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.BOTTOM_IDS.getClasses())
          ))
          .addSimplePane(getLayoutBuilder().addPair(rightPane, GameStyleClass.SIDES_IDS.getClasses()));
  }
  
  private HBox createHBox() {
    final HBox hBox = new HBox();
    
    HBox.setHgrow(hBox, Priority.ALWAYS);
    VBox.setVgrow(hBox, Priority.ALWAYS);
    
    return hBox;
  }
  
  private FlowPane createFlowPane(Orientation orientation) {
    final FlowPane flowPane = new FlowPane(orientation);
    flowPane.setVgap(24);
    flowPane.setHgap(32);
    flowPane.setPrefHeight(getScene().getHeight());
    
    return flowPane;
  }
  
  private void mediatorsInitializer(String propertyName) {
    switch (propertyName) {
      case "cards":
        this.onCards();
        break;
      case "playerDetails":
        this.setHighCardMediator();
        break;
    }
  }
  
  private void onCards() {
    CardsMediator cardsMediator = this.getMediatorsFactory().createCardsMediator(this.getGameObjectViewFactory(), this.getCardsViewFactory(), this.getGame().getCards(), this.getGame().getPlayerDetails());
    cardsMediator.setGameController(this.getGameController());
    
    this.getGameMediator().setCardsMediator(cardsMediator);
  }
  
  private void setHighCardMediator() {
    if (game.getPlayerDetails().stream().noneMatch(playerDetail -> playerDetail.getHighCard() != null)) {
      this.highCardsMediator = this.getMediatorsFactory().createHighCardMediator();
      this.getGameMediator().setHighCardMediator(this.getHighCardsMediator());
    }
  }
  
  private Game getGame() {
    return this.game;
  }
  
  private GameController getGameController() {
    return this.gameController;
  }
  
  private GameObjectViewFactory getGameObjectViewFactory() {
    return this.gameObjectViewFactory;
  }
  
  private CardsViewFactory getCardsViewFactory() {
    return this.cardsViewFactory;
  }
  
  private DealerController getDealerController() {
    return this.dealerController;
  }
  
  private GameMediator getGameMediator() {
    return this.gameMediator;
  }
  
  private HighCardMediator getHighCardsMediator() {
    return this.highCardsMediator;
  }
  
  private MediatorsFactory getMediatorsFactory() {
    return this.mediatorsFactory;
  }
}
