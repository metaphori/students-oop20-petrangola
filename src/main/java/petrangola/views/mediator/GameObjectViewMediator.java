package main.java.petrangola.views.mediator;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.ViewNodeFactory;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.DealerView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.PlayerView;
import main.java.petrangola.views.player.UserView;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class GameObjectViewMediator implements GameMediator {
  private static final Pair<Vertical, Horizontal> BOARD_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
  private static final Pair<Vertical, Horizontal> USER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final Pair<Vertical, Horizontal> DEALER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final int THRESHOLD_NPC = 6;
  
  private final LayoutBuilder layoutBuilder;
  private final Dealer dealer;
  private final ViewNodeFactory viewNodeFactory;
  private final CardsViewFactory cardsViewFactory;
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playersDetails;
  private final List<NPCView> npcViews = new CopyOnWriteArrayList<>();
  
  private int npcIndex = 0;
  private BoardView boardView;
  private UserView userView;
  private DealerView dealerView;
  
  public GameObjectViewMediator(ViewNodeFactory viewNodeFactory, CardsViewFactory cardsViewFactory, Game game, LayoutBuilder layoutBuilder) {
    this.viewNodeFactory = viewNodeFactory;
    this.cardsViewFactory = cardsViewFactory;
    this.cardsList = game.getCards();
    this.playersDetails = game.getPlayerDetails();
    this.layoutBuilder = layoutBuilder;
    this.dealer = game.getDealer();
    
    init();
  }
  
  private void init() {
    this.cardsList
          .stream()
          .map(this::getCardsViewMethod)
          .forEachOrdered(pair -> {
            final PlayerDetail playerDetail = getPlayerDetail(pair.getX().getCards().getPlayer());
            
            switch (pair.getY()) {
              case BOARD:
                this.boardView = viewNodeFactory.createBoardView(pair.getX());
                break;
              case NPC:
                this.npcViews.add(viewNodeFactory.createNPCView(playerDetail, pair.getX()));
                break;
              case USER:
                this.userView = viewNodeFactory.createUserView(playerDetail, pair.getX());
                break;
              case DEALER:
                this.dealerView = viewNodeFactory.createDealerView(playerDetail, pair.getX());
                break;
              default:
                throw new IllegalStateException();
            }
          });
  }
  
  @Override
  public void register(Pane pane) {
    registerTop(pane);
    registerCentral(pane);
    registerBottom(pane);
    registerSides(pane);
  }
  
  @Override
  public void unregister(Pane layout) {
    // empty for now
  }
  
  private void registerSides(Pane pane) {
    final List<FlowPane> sidePanes = pane.lookupAll(GameStyleClass.NPC_CARDS.getAsStyleClass())
                                           .stream()
                                           .map(node -> (FlowPane) node)
                                           .collect(Collectors.toList());
    
    this.npcViews.forEach(npcView -> {
      final Pair<Vertical, Horizontal> position = npcView.getCardsView().getPosition();
      final int index = position.getY().equals(Horizontal.RIGHT) ? 1 : 0;
      
      sidePanes.get(index).setAlignment(layoutBuilder.getPos(position));
      sidePanes.get(index).getChildren().add(npcView.getCardsView().get());
    });
  }
  
  private void registerBottom(Pane pane) {
    final HBox hBox = new HBox();
    final HBox bottomPane = (HBox) pane.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
    final PlayerView playerView = this.getUserPlayerView();
    
    final Pos pos = this.layoutBuilder.getPos(playerView.getCardsView().getPosition());
    final AbstractButtonFX exchangeButton = playerView.getExchangeButton();
    final AbstractButtonFX knockButton = playerView.getKnockButton();
    
    exchangeButton.get().setDisable(false);
    knockButton.get().setDisable(false);
    
    hBox.getChildren().addAll(playerView.getCardsView().get().getChildren());
    
    hBox.setAlignment(pos);
    hBox.setSpacing(8);
    
    bottomPane.setAlignment(pos);
    bottomPane.setSpacing(24);
    bottomPane.getChildren().addAll(hBox, exchangeButton.get(), knockButton.get());
  }
  
  private void registerCentral(Pane pane) {
    final HBox hBox = new HBox();
    final HBox centralPane = (HBox) pane.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
    final Pos pos = this.layoutBuilder.getPos(boardView.getCardsView().getPosition());
    
    hBox.setAlignment(pos);
    hBox.setSpacing(8);
    hBox.getChildren().addAll(boardView.getCardsView().get().getChildren());
    
    centralPane.setAlignment(pos);
    centralPane.getChildren().add(hBox);
  }
  
  private void registerTop(Pane pane) {
    final HBox topPane = (HBox) pane.lookup(GameStyleClass.LIFE.getAsStyleClass());
    topPane.getChildren().add(new Text());
  }
  
  private Pair<CardsView<Group>, GAME_OBJECT> getCardsViewMethod(final Cards cards) {
    Pair<CardsView<Group>, GAME_OBJECT> pair = null;
    
    switch (this.getGameObject(cards)) {
      case USER:
        pair = new Pair<>(this.cardsViewFactory.createUserCards(cards, USER_POSITION), GAME_OBJECT.USER);
        break;
      case NPC:
        this.npcIndex++;
        pair = new Pair<>(this.cardsViewFactory.createOpponentCards(cards, npcIndex, THRESHOLD_NPC), GAME_OBJECT.NPC);
        break;
      case DEALER:
        pair = new Pair<>(this.cardsViewFactory.createDealerCards(cards, DEALER_POSITION), GAME_OBJECT.DEALER);
        break;
      case BOARD:
        pair = new Pair<>(this.cardsViewFactory.createBoardCards(cards, BOARD_POSITION), GAME_OBJECT.BOARD);
        break;
    }
    
    return pair;
  }
  
  private PlayerView getUserPlayerView() {
    if (this.userView == null) {
      return this.dealerView;
    }
    
    return this.userView;
  }
  
  private PlayerDetail getPlayerDetail(Optional<Player> player) {
    if (player.isEmpty()) {
      return null;
    }
    
    final Optional<PlayerDetail> playerDetail = this.playersDetails
                                                      .stream()
                                                      .filter(element -> element.getPlayer().equals(player.get()))
                                                      .findFirst();
    
    if (playerDetail.isEmpty()) {
      throw new IllegalStateException();
    }
    
    return playerDetail.get();
  }
  
  @Override
  public void initDealerView(GameController gameController, DealerController dealerController, Cards boardCards) {
    this.dealerView.init(gameController, dealerController, boardCards);
  }
  
  @Override
  public void showDealerView(final Pane layout) {
    final AbstractButtonFX acceptDealtCardsButton = this.dealerView.getAcceptDealtCardsButton();
    final AbstractButtonFX firstExchangeButton = this.dealerView.getFirstExchangeButton();
    
    this.dealerView.getExchangeButton().setDisable(true);
    this.dealerView.getKnockButton().setDisable(true);
    this.dealerView.getExchangeButton().get().setVisible(false);
    this.dealerView.getKnockButton().get().setVisible(false);
    
    acceptDealtCardsButton.get().setVisible(true);
    firstExchangeButton.get().setVisible(true);
    
    final HBox bottomPane = (HBox) layout.lookup(GameStyleClass.DEALER_BUTTONS.getAsStyleClass());
    bottomPane.getChildren().addAll(acceptDealtCardsButton.get(), firstExchangeButton.get());
  }
  
  @Override
  public void hideDealerView() {
    final AbstractButtonFX acceptDealtCardsButton = this.dealerView.getAcceptDealtCardsButton();
    final AbstractButtonFX firstExchangeButton = this.dealerView.getFirstExchangeButton();
    
    this.dealerView.getExchangeButton().setDisable(false);
    this.dealerView.getKnockButton().setDisable(false);
    this.dealerView.getExchangeButton().get().setVisible(true);
    this.dealerView.getKnockButton().get().setVisible(true);
    
    acceptDealtCardsButton.get().setVisible(false);
    firstExchangeButton.get().setVisible(false);
  }
  
  @Override
  public void update(String propertyName, Object newValue) {
    System.out.println("updating in mediator: " + propertyName  + "   " +  newValue);
    switch (propertyName) {
      case "clearChosenCards":
        this.clearChosenCards();
        break;
      case "exchangedCards":
        this.getUserPlayerView()
              .getExchangeButton()
              .setData((CardsExchanged) newValue);
        
        break;
      case "round":
        if (newValue.equals(1)) {
          this.showBoardCards();
          this.getUserPlayerView().getCardsView().showCards();
        }
        
        break;
      case "currentPlayer":
        this.toggleUserButton((Player) newValue);
        
        break;
      case "firstExchange":
        this.showBoardCards();
        
      case "exchange":
        this.updateCards((List<Cards>) newValue);
        
        break;
    }
  }
  
  private void clearChosenCards() {
    getUserPlayerView().getCardsView().getCardViews().forEach(CardView::clearChosen);
    getUserPlayerView().getExchangeButton().setData(null);
    this.boardView.getCardsView().getCardViews().forEach(CardView::clearChosen);
  }
  
  private void toggleUserButton(Player player) {
    if (!player.isNPC()) {
      getUserPlayerView().getExchangeButton().get().setVisible(true);
      getUserPlayerView().getKnockButton().get().setVisible(true);
    } else {
      getUserPlayerView().getExchangeButton().get().setVisible(false);
      getUserPlayerView().getKnockButton().get().setVisible(false);
    }
  }
  
  private void showBoardCards() {
    this.boardView.getCardsView().showCards();
  }
  
  private void updateCards(List<Cards> cardsList) {
    cardsList.forEach(cards -> {
      // Not the nicest solution, probably a bad pattern BUT, its saved me
      CardsView<Group> cardsView = null;
      
      switch (this.getGameObject(cards)) {
        case NPC:
          cardsView = this.npcViews
                            .stream()
                            .filter(npcView -> npcView.getPlayer().equals(cards.getPlayer().get()))
                            .findFirst()
                            .get()
                            .getCardsView();
          break;
        case USER:
          cardsView = this.userView.getCardsView();
          break;
        case BOARD:
          cardsView = this.boardView.getCardsView();
          break;
        case DEALER:
          cardsView = this.dealerView.getCardsView();
          break;
      }
      
      cardsView.setCards(cards);
      
      if ((cards.isPlayerCards() && !cards.getPlayer().get().isNPC()) || cards.isCommunity())  {
        cardsView.update(cards);
      }
    });
  }
  
  private GAME_OBJECT getGameObject(Cards cards) {
    if (cards.isCommunity()) {
      return GAME_OBJECT.BOARD;
    }
    
    final Optional<Player> player = cards.getPlayer();
    
    if (player.isEmpty()) {
      throw new IllegalStateException();
    }
  
    if (player.get().isNPC()) {
      return GAME_OBJECT.NPC;
    }
    
    if (dealer.getUsername().equals(player.get().getUsername())) {
      return GAME_OBJECT.DEALER;
    }
  
    System.out.println(player.get().getUsername() + "  " + player.get().isNPC());
    
    return GAME_OBJECT.USER;
  }
  
  private enum GAME_OBJECT {
    USER,
    NPC,
    DEALER,
    BOARD
  }
}
