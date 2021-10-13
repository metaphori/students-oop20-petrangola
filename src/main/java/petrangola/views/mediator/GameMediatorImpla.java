//package main.java.petrangola.views.mediator;
//
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Text;
//import main.java.petrangola.models.cards.Cards;
//import main.java.petrangola.models.game.Game;
//import main.java.petrangola.models.player.Dealer;
//import main.java.petrangola.models.player.Player;
//import main.java.petrangola.models.player.PlayerDetail;
//import main.java.petrangola.utlis.Pair;
//import main.java.petrangola.utlis.position.Horizontal;
//import main.java.petrangola.utlis.position.Vertical;
//import main.java.petrangola.views.GameObjectViewFactory;
//import main.java.petrangola.views.board.BoardView;
//import main.java.petrangola.views.cards.CardView;
//import main.java.petrangola.views.cards.CardsExchanged;
//import main.java.petrangola.views.cards.CardsView;
//import main.java.petrangola.views.cards.CardsViewFactory;
//import main.java.petrangola.views.components.button.AbstractButtonFX;
//import main.java.petrangola.views.components.layout.LayoutBuilder;
//import main.java.petrangola.views.game.*;
//import main.java.petrangola.views.player.DealerView;
//import main.java.petrangola.views.player.NPCView;
//import main.java.petrangola.views.player.PlayerView;
//import main.java.petrangola.views.player.UserView;
//import main.java.petrangola.views.player.buttons.ExchangeButton;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.stream.Collectors;
//
//public class GameMediatorImpla implements UpdatableMediator {
//  private static final Pair<Vertical, Horizontal> BOARD_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
//  private static final Pair<Vertical, Horizontal> USER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
//  private static final Pair<Vertical, Horizontal> DEALER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
//  private static final int THRESHOLD_NPC = 6;
//
//  private final LayoutBuilder layoutBuilder;
//  private final Dealer dealer;
//  private final GameObjectViewFactory viewNodeFactory;
//  private final CardsViewFactory cardsViewFactory;
//  private final List<Cards> cardsList;
//  private final List<PlayerDetail> playersDetails;
//  private final List<NPCView> npcViews = new CopyOnWriteArrayList<>();
//
//  private final RoundView roundView = new RoundViewImpl(new Text());
//  private final WinnerView winnerView = new WinnerViewImpl(new Text());
//  private final KnockView knockView = new KnockViewImpl(new Text());
//
//  private int npcIndex = 0;
//  private BoardView boardView;
//  private UserView userView;
//  private DealerView dealerView;
//
//  public GameMediatorImpla(GameObjectViewFactory viewNodeFactory, CardsViewFactory cardsViewFactory, Game game, LayoutBuilder layoutBuilder) {
//    this.viewNodeFactory = viewNodeFactory;
//    this.cardsViewFactory = cardsViewFactory;
//    this.cardsList = game.getCards();
//    this.playersDetails = game.getPlayerDetails();
//    this.layoutBuilder = layoutBuilder;
//    this.dealer = game.getDealer();
//
//    init();
//  }
//
//  private void init() {
//    this.cardsList
//          .stream()
//          .map(this::getCardsViewMethod)
//          .forEachOrdered(pair -> {
//            final PlayerDetail playerDetail = getPlayerDetail(pair.getX().getCards().getPlayer());
//
//            switch (pair.getY()) {
//              case BOARD:
//                this.boardView = viewNodeFactory.createBoardView();
//                break;
//              case NPC:
//                this.npcViews.add(viewNodeFactory.createNPCView(playerDetail));
//                break;
//              case USER:
//                this.userView = viewNodeFactory.createUserView(playerDetail);
//                break;
//              case DEALER:
//                this.dealerView = viewNodeFactory.createDealerView(playerDetail);
//                break;
//              default:
//                throw new IllegalStateException();
//            }
//          });
//  }
//
//  @Override
//  public void register(Pane pane) {
//    registerTop(pane);
//    registerCentral(pane);
//    registerBottom(pane);
//    registerSides(pane);
//  }
//
//  @Override
//  public void unregister(Pane layout) {
//    final List<FlowPane> sidePanes = layout.lookupAll(GameStyleClass.NPC_CARDS.getAsStyleClass())
//                                           .stream()
//                                           .map(node -> (FlowPane) node)
//                                           .collect(Collectors.toList());
//
//    sidePanes.forEach(sidePane -> sidePane.getChildren().clear());
//
//    final Pane userPane = (Pane) layout.lookup(GameStyleClass.USERNAME.getAsStyleClass());
//    userPane.getChildren().clear();
//
//    final Pane userCardsPane = (Pane) layout.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
//    userCardsPane.getChildren().clear();
//
//    final Pane userActionsPane = (Pane) layout.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
//    userActionsPane.getChildren().clear();
//
//    final Pane boardPane = (Pane) layout.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
//    boardPane.getChildren().clear();
//  }
//
//  private void registerSides(Pane pane) {
//    final List<FlowPane> sidePanes = pane.lookupAll(GameStyleClass.NPC_CARDS.getAsStyleClass())
//                                           .stream()
//                                           .map(node -> (FlowPane) node)
//                                           .collect(Collectors.toList());
//
//    this.npcViews.forEach(npcView -> {
//      final Pair<Vertical, Horizontal> position = npcView.getCardsView().getPosition();
//      final int index = position.getY().equals(Horizontal.RIGHT) ? 1 : 0;
//
//      sidePanes.get(index).setAlignment(layoutBuilder.getPos(position));
//      sidePanes.get(index).getChildren().add(npcView.getCardsView().get());
//    });
//  }
//
//  private void registerBottom(Pane pane) {
//    final HBox cardsHBox = new HBox();
//    final Pane userCardsPane = (Pane) pane.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
//    final PlayerView playerView = this.getUserPlayerView();
//
//    final Pos cardsViewPosition = this.layoutBuilder.getPos(playerView.getCardsView().getPosition());
//    cardsHBox.setAlignment(cardsViewPosition);
//    cardsHBox.setSpacing(8);
//    cardsHBox.getChildren().addAll(playerView.getCardsView().get().getChildren());
//
//    userCardsPane.getChildren().add(cardsHBox);
//
//    final HBox actionsHBox = new HBox();
//    final Pane userActionsPane = (Pane) pane.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
//    final AbstractButtonFX exchangeButton = playerView.getExchangeButton();
//    final AbstractButtonFX knockButton = playerView.getKnockButton();
//
//    exchangeButton.get().setDisable(true);
//    knockButton.get().setDisable(false);
//
//    actionsHBox.getChildren().addAll(exchangeButton.get(), knockButton.get());
//    actionsHBox.setSpacing(24);
//    actionsHBox.setAlignment(Pos.CENTER);
//
//    userActionsPane.getChildren().add(actionsHBox);
//  }
//
//  private void registerCentral(Pane pane) {
//    final HBox hBox = new HBox();
//    final Pane boardPane = (Pane) pane.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
//    final Pos pos = this.layoutBuilder.getPos(boardView.getCardsView().getPosition());
//
//    hBox.setAlignment(pos);
//    hBox.setSpacing(8);
//    hBox.getChildren().addAll(boardView.getCardsView().get().getChildren());
//
//    boardPane.getChildren().add(hBox);
//
//    this.centerParent(boardPane.getParent());
//  }
//
//  private void centerParent(Parent parent) {
//    if (parent instanceof HBox) {
//      HBox hBox = (HBox) parent;
//      hBox.setAlignment(Pos.CENTER);
//    }
//  }
//
//  private void registerTop(Pane pane) {
//    final Pane userActionPane = (Pane) pane.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
//    userActionPane.getChildren().add(getUserPlayerView().getUsernameView().get());
//  }
//
//  private Pair<CardsView<Group>, GAME_OBJECT> getCardsViewMethod(final Cards cards) {
//    Pair<CardsView<Group>, GAME_OBJECT> pair = null;
//
//    switch (this.getGameObject(cards)) {
//      case USER:
//        pair = new Pair<>(this.cardsViewFactory.createUserCards(cards, USER_POSITION), GAME_OBJECT.USER);
//        break;
//      case NPC:
//        this.npcIndex++;
//        pair = new Pair<>(this.cardsViewFactory.createOpponentCards(cards, npcIndex, THRESHOLD_NPC), GAME_OBJECT.NPC);
//        break;
//      case DEALER:
//        pair = new Pair<>(this.cardsViewFactory.createDealerCards(cards, DEALER_POSITION), GAME_OBJECT.DEALER);
//        break;
//      case BOARD:
//        pair = new Pair<>(this.cardsViewFactory.createBoardCards(cards, BOARD_POSITION), GAME_OBJECT.BOARD);
//        break;
//    }
//
//    return pair;
//  }
//
//  private PlayerView getPlayerView(Player player) {
//    if (player.isNPC()) {
//      return this.npcViews
//                   .stream()
//                   .filter(npcView -> npcView.getPlayer().equals(player))
//                   .findAny()
//                   .get();
//    }
//
//    return this.getUserPlayerView();
//  }
//
//  private PlayerView getUserPlayerView() {
//    if (this.userView == null) {
//      return this.dealerView;
//    }
//
//    return this.userView;
//  }
//
//  private PlayerDetail getPlayerDetail(Optional<Player> player) {
//    if (player.isEmpty()) {
//      return null;
//    }
//
//    final Optional<PlayerDetail> playerDetail = this.playersDetails
//                                                      .stream()
//                                                      .filter(element -> element.getPlayer().equals(player.get()))
//                                                      .findFirst();
//
//    if (playerDetail.isEmpty()) {
//      throw new IllegalStateException();
//    }
//
//    return playerDetail.get();
//  }
//
//  @Override
//  public void update(String propertyName, Object newValue) {
//    switch (propertyName) {
//      case "clearChosenCards":
//        this.clearChosenCards();
//        break;
//      case "exchangedCards":
//        ExchangeButton exchangeButton = (ExchangeButton) this.getUserPlayerView().getExchangeButton();
//        CardsExchanged cardsExchanged = (CardsExchanged) newValue;
//
//        exchangeButton.setData(cardsExchanged);
//        this.enableExchangeButton(cardsExchanged, exchangeButton);
//
//        break;
//      case "round":
//        if (newValue.equals(1)) {
//          this.showBoardCards();
//          this.getUserPlayerView().getCardsView().showCards();
//        }
//
//        updateRoundTextView(String.valueOf(newValue));
//
//        break;
//      case "currentPlayer":
//        final Player player = (Player) newValue;
//        this.toggleUserButton(player);
//        this.updateUsernameView(player);
//
//        break;
//      case "playerLives":
//        this.updateLifeView((PlayerDetail) newValue);
//        break;
//      case "winner":
//        this.npcViews.forEach(npcView -> npcView.getCardsView().showCards());
//        this.updateWinnerView(String.valueOf(newValue));
//        break;
//      case "firstExchange":
//        this.showBoardCards();
//
//      case "exchange":
//        final List<Cards> cardsList = (List<Cards>) newValue;
//        this.updateCards(cardsList);
//
//        break;
//    }
//  }
//
//  private void enableExchangeButton(CardsExchanged cardsExchanged, ExchangeButton exchangeButton) {
//    cardsExchanged
//          .getBoardCards()
//          .ifPresent(boardCards -> {
//            cardsExchanged
//                  .getPlayerCards()
//                  .ifPresent(playerCards -> exchangeButton.setDisable(!cardsExchanged.areExchangeable(boardCards, playerCards)));
//          });
//  }
//
//  private Pane getLayout() {
//    return this.layoutBuilder.getLayout();
//  }
//
//
//  private void clearChosenCards() {
//    getUserPlayerView().getCardsView().getCardViews().forEach(CardView::clearChosen);
//    getUserPlayerView().getExchangeButton().setData(null);
//    this.boardView.getCardsView().getCardViews().forEach(CardView::clearChosen);
//  }
//
//  private void toggleUserButton(Player player) {
//    if (!player.isNPC()) {
//      getUserPlayerView().getExchangeButton().get().setVisible(true);
//      getUserPlayerView().getKnockButton().get().setVisible(true);
//    } else {
//      getUserPlayerView().getExchangeButton().get().setVisible(false);
//      getUserPlayerView().getKnockButton().get().setVisible(false);
//    }
//  }
//
//  private void showBoardCards() {
//    this.boardView.getCardsView().showCards();
//  }
//
//  private void updateCards(List<Cards> cardsList) {
//    cardsList.forEach(cards -> {
//      // Not the nicest solution, probably a bad pattern BUT, its saved me
//      CardsView<Group> cardsView = null;
//
//      switch (this.getGameObject(cards)) {
//        case NPC:
//          cardsView = this.npcViews
//                            .stream()
//                            .filter(npcView -> npcView.getPlayer().equals(cards.getPlayer().get()))
//                            .findFirst()
//                            .get()
//                            .getCardsView();
//          break;
//        case USER:
//          cardsView = this.userView.getCardsView();
//          break;
//        case BOARD:
//          cardsView = this.boardView.getCardsView();
//          break;
//        case DEALER:
//          cardsView = this.dealerView.getCardsView();
//          break;
//      }
//
//      cardsView.setCards(cards);
//      cardsView.update(cards);
//    });
//  }
//
//
//  private GAME_OBJECT getGameObject(Cards cards) {
//    if (cards.isCommunity()) {
//      return GAME_OBJECT.BOARD;
//    }
//
//    final Optional<Player> player = cards.getPlayer();
//
//    if (player.isEmpty()) {
//      throw new IllegalStateException();
//    }
//
//    if (player.get().isNPC()) {
//      return GAME_OBJECT.NPC;
//    }
//
//    if (dealer.getUsername().equals(player.get().getUsername())) {
//      return GAME_OBJECT.DEALER;
//    }
//
//    return GAME_OBJECT.USER;
//  }
//
//  private enum GAME_OBJECT {
//    USER,
//    NPC,
//    DEALER,
//    BOARD
//  }
//}
