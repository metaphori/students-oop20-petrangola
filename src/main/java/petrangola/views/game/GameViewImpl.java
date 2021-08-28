package main.java.petrangola.views.game;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.game.GameControllerImpl;
import main.java.petrangola.controllers.player.DealerControllerImpl;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.controllers.player.PlayerControllerImpl;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.models.player.Player;
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
import main.java.petrangola.views.events.EventManagerImpl;
import main.java.petrangola.views.events.NextRoundEvent;
import main.java.petrangola.views.events.NextTurnEvent;
import main.java.petrangola.views.mediator.GameMediator;
import main.java.petrangola.views.mediator.GameObjectViewMediator;
import main.java.petrangola.views.mediator.HighCardMediator;
import main.java.petrangola.views.mediator.Mediator;
import org.greenrobot.eventbus.EventBus;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class GameViewImpl extends AbstractViewFX implements GameView {
  private final Game game = new GameImpl();
  private final CardsExchanged cardsExchanged = new CardsExchangedImpl();
  private final PlayerController playerController = new PlayerControllerImpl();
  private final GameController gameController = new GameControllerImpl(game);
  private final EventManagerImpl eventManager = new EventManagerImpl(gameController);
  private final ViewNodeFactory viewNodeFactory = new ViewNodeFactoryImpl(game, playerController);
  private final CardsViewFactory cardsViewFactory = new CardsViewFactoryImpl();
  private final DealerTextView dealerTextView = new DealerTextViewImpl(new Text());
  private final RoundView roundView = new RoundViewImpl(new Text());
  private final WinnerView winnerView = new WinnerViewImpl(new Text());
  private final KnockView knockView = new KnockViewImpl(new Text());
  private final Timeline timeline = new Timeline();
  private final LayoutBuilder layoutBuilder;
  
  private GameMediator gameObjectViewMediator;
  private Mediator highCardsMediator;
  private DealerControllerImpl dealerController;
  private Player currentPlayer;
  
  private boolean highCardsAreShown = false;
  private Cards boardCards;
  private Cards currentPlayerCards;
  private int currentTurnNumber;
  
  
  public GameViewImpl(Stage stage, Option option) {
    super(stage, new HBox());
    
    addListenerToModel(game);
    
    eventManager.register();
    
    getLayout().setStyle("-fx-background-image: url('" + Background.GAME.getPath() + "');" +
                               "-fx-background-repeat: no-repeat;" +
                               "-fx-background-size: cover;" +
                               "-fx-background-position: center center;");
    
    this.layoutBuilder = new LayoutBuilderImpl(getLayout(), Horizontal.values());
    
    final FlowPane leftPane = new FlowPane(Orientation.VERTICAL);
    leftPane.setVgap(24);
    leftPane.setHgap(32);
    leftPane.setPrefHeight(getScene().getHeight());
    
    final FlowPane rightPane = new FlowPane(Orientation.VERTICAL);
    rightPane.setVgap(24);
    rightPane.setHgap(32);
    rightPane.setPrefHeight(getScene().getHeight());
    
    leftPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    rightPane.setStyle("-fx-padding: 32px; -fx-border-insets: 32px; -fx-background-insets: 32px;");
    
    final HBox topHBox = new HBox();
    HBox.setHgrow(topHBox, Priority.ALWAYS);
    VBox.setVgrow(topHBox, Priority.ALWAYS);
    
    final HBox centralHBox = new HBox();
    HBox.setHgrow(centralHBox, Priority.ALWAYS);
    VBox.setVgrow(centralHBox, Priority.ALWAYS);
    
    final HBox bottomHBox = new HBox();
    HBox.setHgrow(bottomHBox, Priority.ALWAYS);
    VBox.setVgrow(bottomHBox, Priority.ALWAYS);
    
    this.layoutBuilder
          .addSimplePane(layoutBuilder.addPair(leftPane, GameStyleClass.SIDES_IDS.getClasses()))
          .addVBox(List.of(
                layoutBuilder.addPair(topHBox, GameStyleClass.TOP_IDS.getClasses()),
                layoutBuilder.addPair(centralHBox, GameStyleClass.CENTRAL_IDS.getClasses()),
                layoutBuilder.addPair(bottomHBox, GameStyleClass.BOTTOM_IDS.getClasses())
          ))
          .addSimplePane(layoutBuilder.addPair(rightPane, GameStyleClass.SIDES_IDS.getClasses()));
    
    this.init(option);
  }
  
  private void init(Option option) {
    this.gameController.createPlayers(option.getUsername(), option.getDifficultyLevel(), option.getOpponentsSize());
    this.gameController.createPlayerDetails();
    this.gameController.createBoard();
    this.gameController.createHighCards();
    this.gameController.setDealer();
  }
  
  @Override
  public void showWinner() {
    this.winnerView.show();
  }
  
  @Override
  public void showKnocks() {
    this.knockView.show();
  }
  
  @Override
  public void showRound() {
    this.roundView.show();
  }
  
  public void showDealerName() {
    final Pane usernamePane = (Pane) getLayout().lookup(GameStyleClass.USERNAME.getAsStyleClass());
    
    this.dealerTextView.setCurrentDealerName(this.game.getDealer().getUsername());
    this.dealerTextView.show();
    
    usernamePane.getChildren().add(this.dealerTextView.get());
  }
  
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    switch (evt.getPropertyName()) {
      case "updatedCombination":
        this.handleExchangedCards((Cards) evt.getSource());
        this.gameObjectViewMediator.update("exchangedCards", this.cardsExchanged);
        
        break;
      case "cards":
        this.game.getCards().forEach(this::addListenerToModel);
        this.game.getPlayers().forEach(player -> System.out.println("username: " + player.getUsername() + "  isNpc:" + player.isNPC() + " isDealer:" + player.isDealer()));
        this.setCurrentPlayerCards(this.game.getCards());
        
        break;
      case "players":
        this.game.getPlayers().forEach(this::addListenerToModel);
        
        break;
      case "playerDetails":
        this.game.getPlayerDetails().forEach(this::addListenerToModel);
        
        break;
      case "dealer":
        this.addListenerToModel(this.game.getDealer());
        this.gameController.setTurnNumbers();
        this.setCurrentPlayer();
        
        this.beforeStartingGameAnimation();
        
        break;
      case "round":
        if (!dealerIsNotAUser()) {
          this.gameObjectViewMediator.hideDealerView();
        }
        
        break;
      case "currentTurnNumber":
        this.currentTurnNumber = (int) evt.getNewValue();
        
        break;
      case "knockerCount":
        break;
      case "lastKnocker":
        break;
      case "winner":
        break;
      case "dealtCards":
        final List<Cards> cardsList = (List<Cards>) evt.getNewValue();
        this.game.setCards(cardsList);
        this.setBoardCards(cardsList);
        
        this.registerComponents();
        
        if (dealerIsNotAUser() && this.boardCards != null && this.currentPlayerCards != null) {
          EventBus.getDefault().post(new NextRoundEvent());
          this.dealerController.cherryPickingCombination(this.boardCards, this.currentPlayerCards);
        }
        break;
      case "onlyOneRound":
        this.gameController.onlyOneRound();
      case "exchange":
      case "firstExchange":
        this.setCurrentPlayer();
        this.setCurrentPlayerCards(this.game.getCards());
        this.gameObjectViewMediator.update(evt.getPropertyName(), evt.getNewValue());
  
        EventBus.getDefault().post(new NextTurnEvent());
        
        if (this.currentPlayer.isNPC()) {
          this.playerController.exchangeCards(this.currentPlayer, boardCards, this.currentPlayerCards);
        }
        
        break;
    }
    
    if (!this.highCardsAreShown && highCardsArePresent()) {
      this.highCardsAreShown = true;
      this.highCardsMediator = new HighCardMediator(this.game.getPlayerDetails());
      this.highCardsMediator.register(getLayout());
    }
  }
  
  private void setCurrentPlayer() {
    this.currentPlayer = this.gameController.getCurrentPlayer();
  }
  
  private void setCurrentPlayerCards(List<Cards> cardsList) {
    cardsList
          .stream()
          .filter(cards -> cards.getPlayer().isPresent())
          .filter(cards -> cards.getPlayer().get().equals(this.currentPlayer))
          .findFirst()
          .ifPresent(cards -> this.currentPlayerCards = cards);
  }
  
  private void registerComponents() {
    this.gameObjectViewMediator = new GameObjectViewMediator(viewNodeFactory, cardsViewFactory, this.game, this.layoutBuilder);
    this.gameObjectViewMediator.register(getLayout());
  
    if (this.boardCards != null) {
      this.gameObjectViewMediator.initDealerView(this.gameController, this.dealerController, this.boardCards);
    }
    
    if (!dealerIsNotAUser()) {
      this.gameObjectViewMediator.showDealerView(getLayout());
    }
  }
  
  private void setBoardCards(List<Cards> cardsList) {
    cardsList
          .stream()
          .filter(Cards::isCommunity)
          .findFirst()
          .ifPresent(cards -> {
            this.boardCards = cards;
          });
  }
  
  private boolean dealerIsNotAUser() {
    return this.game.getPlayers().stream().anyMatch(player -> !player.isDealer() && !player.isNPC());
  }
  
  private void handleExchangedCards(Cards cards) {
    this.cardsExchanged.addCards(cards);
  }
  
  private void beforeStartingGameAnimation() {
    final KeyFrame keyFrame1 = new KeyFrame(Duration.millis(600), (actionEvent) -> this.showDealerName());
    
    final KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2600), (actionEvent) -> {
      this.highCardsMediator.unregister(getLayout());
      this.dealerTextView.hide();
      
      final Pane pane = (Pane) getLayout().lookup(GameStyleClass.USERNAME.getAsStyleClass());
      getLayout().getChildren().removeAll(pane.getChildren());
    });
    
    final KeyFrame keyFrame3 = new KeyFrame(Duration.millis(5000), (actionEvent) -> {
      this.dealerController = new DealerControllerImpl(this.game.getDealer());
      this.dealerController.dealCards(this.game.getPlayerDetails(), this.game.getBoard());
    });
    
    
    this.timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
    
    this.timeline.play();
  }
  
  private boolean highCardsArePresent() {
    return checkPlayerDetailsIsFullyInitialized() && this.game.getPlayerDetails().stream().noneMatch(playerDetail -> playerDetail.getHighCard() == null);
  }
  
  private boolean checkPlayerDetailsIsFullyInitialized() {
    return this.game.getPlayerDetails() != null && this.game.getPlayerDetails().size() == this.game.getPlayers().size();
  }
}
