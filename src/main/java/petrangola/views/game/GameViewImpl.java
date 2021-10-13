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
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameImpl;
import main.java.petrangola.models.option.Option;
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
  
  private final EventManagerImpl eventManager = new EventManagerImpl(gameController, playerController);
  
  private final GameObjectViewFactory gameObjectViewFactory = new GameObjectViewFactoryImpl(game, playerController, dealerController, getLayout());
  private final CardsViewFactory cardsViewFactory = new CardsViewFactoryImpl();
  private final MediatorsFactory mediatorsFactory = new MediatorsFactoryImpl();
  
  private final GameMediator gameMediator;
  private HighCardMediator highCardsMediator;
  
  public GameViewImpl(Stage stage, Option option) {
    super(stage, new HBox(), Horizontal.values());
    
    addListenerToModel(game);
    
    this.eventManager.register();
    
    getLayout().setStyle("-fx-background-image: url('" + Background.GAME.getPath() + "');" +
                               "-fx-background-repeat: no-repeat;" +
                               "-fx-background-size: cover;" +
                               "-fx-background-position: center center;");
    
    final FlowPane leftPane = createFlowPane(Orientation.VERTICAL);
    final FlowPane rightPane = createFlowPane(Orientation.VERTICAL);
    
    leftPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    rightPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    
    getLayoutBuilder()
          .addSimplePane(getLayoutBuilder().addPair(leftPane, GameStyleClass.SIDES_IDS.getClasses()))
          .addVBox(List.of(
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.TOP_IDS.getClasses()),
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.CENTRAL_IDS.getClasses()),
                getLayoutBuilder().addPair(createHBox(), GameStyleClass.BOTTOM_IDS.getClasses())
          ))
          .addSimplePane(getLayoutBuilder().addPair(rightPane, GameStyleClass.SIDES_IDS.getClasses()));
    
    this.init(option);
    
    this.gameMediator = mediatorsFactory.createGameMediator(getLayoutBuilder(), this.gameController);
  }
  
  private void init(Option option) {
    this.gameController.createPlayers(option.getUsername(), option.getDifficultyLevel(), option.getOpponentsSize());
    this.gameController.createPlayerDetails();
    this.gameController.createBoard();
    this.gameController.createHighCards();
    this.gameController.setDealer();
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
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    this.mediatorsInitializer(evt.getPropertyName());
    this.gameMediator.update(evt.getPropertyName(), evt.getSource());
  }
  
  private void mediatorsInitializer(String propertyName) {
    switch (propertyName) {
      case "cards":
        CardsMediator cardsMediator = this.mediatorsFactory.createCardsMediator(this.gameObjectViewFactory, this.cardsViewFactory, this.game.getCards(), this.game.getPlayerDetails());
        this.gameMediator.setCardsMediator(cardsMediator);
        
        break;
      case "playerDetails":
        PlayerDetailMediator playerDetailMediator = this.mediatorsFactory.createPlayerDetailMediator();
        this.gameMediator.setPlayerDetailMediator(playerDetailMediator);
        
        this.registerHighCard();
        
        break;
    }
  }
  
  private void registerHighCard() {
    if (game.getPlayerDetails().stream().noneMatch(playerDetail -> playerDetail.getHighCard() != null)) {
      this.highCardsMediator = this.mediatorsFactory.createHighCardMediator(game.getPlayerDetails());
      this.highCardsMediator.register(getLayout());
      this.gameMediator.setHighCardMediator(this.highCardsMediator);
    }
  }
}
