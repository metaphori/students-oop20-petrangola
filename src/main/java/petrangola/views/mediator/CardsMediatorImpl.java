package main.java.petrangola.views.mediator;

import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.GameObjectViewFactory;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardsExchanged;
import main.java.petrangola.views.cards.CardsExchangedImpl;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;
import main.java.petrangola.views.player.*;
import main.java.petrangola.views.player.buttons.ExchangeButton;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class CardsMediatorImpl implements CardsMediator {
  private static final Pair<Vertical, Horizontal> BOARD_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
  private static final Pair<Vertical, Horizontal> USER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final Pair<Vertical, Horizontal> DEALER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final int THRESHOLD_NPC = 6;
  
  private final CardsExchanged cardsExchanged = new CardsExchangedImpl();
  private final List<GameObjectView> viewList = new CopyOnWriteArrayList<>();
  
  private final GameObjectViewFactory gameObjectViewFactory;
  private final CardsViewFactory cardsViewFactory;
  
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playersDetails;
  
  private GameController gameController;
  private LayoutBuilder layoutBuilder;
  
  private int npcIndex = 0;
  
  public CardsMediatorImpl(GameObjectViewFactory gameObjectViewFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playerDetails) {
    this.gameObjectViewFactory = gameObjectViewFactory;
    this.cardsViewFactory = cardsViewFactory;
    this.cardsList = cardsList;
    this.playersDetails = playerDetails;
    
    this.init();
  }
  
  private void init() {
    this.createBoard();
    
    this.cardsList.forEach(cards -> {
      if (cards.isCommunity()) {
        return;
      }
      
      cards.getPlayer().ifPresent(player -> {
        this.playersDetails
              .stream()
              .filter(playerDetail -> playerDetail.getPlayer().equals(player))
              .findFirst()
              .ifPresent(playerDetail -> {
                final Pair<GameObjectView, CardsView<Group>> pair;
                
                if (player.isNPC()) {
                  pair = this.createNPCs(playerDetail, cards);
                } else if (player.isDealer()) {
                  pair = this.createDealer(playerDetail, cards);
                } else {
                  pair = this.createUser(playerDetail, cards);
                }
                
                pair.getX().setCardsView(pair.getY());
                
                pair.getX().addListenerToModel(cards);
                pair.getX().addListenerToModel(playerDetail);
                pair.getX().addListenerToModel(player);
                
                this.getBoardView().addListenerToModel(player);
                
                this.getViewList().add(pair.getX());
              });
      });
    });
  }
  
  private void createBoard() {
    cardsList
          .stream()
          .filter(Cards::isCommunity)
          .findFirst()
          .ifPresent(cards -> {
            BoardView boardView = getGameObjectViewFactory().createBoardView();
            boardView.setCardsView(getCardsViewFactory().createBoardCards(cards, BOARD_POSITION));
            boardView.addListenerToModel(cards);
            boardView.setCardsExchanged(cardsExchanged);
            
            this.getViewList().add(boardView);
          });
  }
  
  private Pair<GameObjectView, CardsView<Group>> createUser(PlayerDetail playerDetail, Cards cards) {
    final UserView userView = getGameObjectViewFactory().createUserView(playerDetail);
    userView.setCardsExchanged(cardsExchanged);
    
    return new Pair<>(userView, getCardsViewFactory().createUserCards(cards, USER_POSITION));
  }
  
  private Pair<GameObjectView, CardsView<Group>> createDealer(PlayerDetail playerDetail, Cards cards) {
    final DealerView dealerView = getGameObjectViewFactory().createDealerView(playerDetail);
    dealerView.setCardsExchanged(cardsExchanged);
    
    return new Pair<>(dealerView, getCardsViewFactory().createDealerCards(cards, DEALER_POSITION));
  }
  
  private Pair<GameObjectView, CardsView<Group>> createNPCs(PlayerDetail playerDetail, Cards cards) {
    npcIndex++;
    return new Pair<>(getGameObjectViewFactory().createNPCView(playerDetail), getCardsViewFactory().createOpponentCards(cards, npcIndex, THRESHOLD_NPC));
  }
  
  @Override
  public void showBoardCards() {
    getBoardView().showCards();
  }
  
  private BoardView getBoardView() {
    return (BoardView) getViewList().get(0);
  }
  
  @Override
  public void showNPCCards() {
    getNPCViews().forEach(GameObjectView::showCards);
  }
  
  @Override
  public void showUserCards() {
    getViewList()
          .stream()
          .filter(gameObjectView -> {
            if (gameObjectView.getCardsView().getCards().isCommunity()) {
              return false;
            }
            
            Optional<Player> player = gameObjectView.getCardsView().getCards().getPlayer();
            
            return player.isEmpty() || !player.get().isNPC();
          })
          .findFirst()
          .ifPresent(GameObjectView::showCards);
  }
  
  @Override
  public void setCurrentPlayerCards(CurrentPlayer currentPlayer) {
    getPlayerView(currentPlayer).ifPresent(gameObjectView -> currentPlayer.setCards(gameObjectView.getCardsView().getCards()));
  }
  
  @Override
  public void toggleUserButton(CurrentPlayer currentPlayer) {
    getUserDealerView().ifPresent(gameObjectView -> gameObjectView.toggleUserButton(currentPlayer.getPlayer()));
  }
  
  @Override
  public void hideDealerView(Pane layout) {
    getUserDealerView().ifPresent(dealerView -> dealerView.hideView(layout));
  }
  
  @Override
  public void showDealerView(Pane layout) {
    getUserDealerView().ifPresent(dealerView -> {
      dealerView.setGameController(getGameController());
      dealerView.init(getBoardView().getCardsView().getCards());
      dealerView.showView(layout);
    });
  }
  
  @Override
  public void register(Pane layout) {
    Objects.requireNonNull(this.getUserPlayerView()).register(layout, getLayoutBuilder());
    
    this.getNPCViews().forEach(npcView -> npcView.register(layout, getLayoutBuilder()));
    
    this.getBoardView().register(layout, getLayoutBuilder());
    this.getBoardView().setExchangeButton((ExchangeButton) Objects.requireNonNull(this.getUserPlayerView()).getExchangeButton());
  }
  
  @Override
  public void unregister(Pane layout) {
    layout.lookupAll(GameStyleClass.NPC_CARDS.getAsStyleClass())
          .stream()
          .map(node -> (FlowPane) node)
          .forEach(sidePane -> sidePane.getChildren().clear());
    
    final Pane userCardsPane = (Pane) layout.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
    userCardsPane.getChildren().clear();
    
    final Pane boardPane = (Pane) layout.lookup(GameStyleClass.BOARD_CARDS.getAsStyleClass());
    boardPane.getChildren().clear();
  
    final Pane userActionsPane = (Pane) layout.lookup(GameStyleClass.USER_ACTIONS.getAsStyleClass());
    userActionsPane.getChildren().clear();
  }
  
  private List<GameObjectView> getViewList() {
    return this.viewList;
  }
  
  private Optional<GameObjectView> getPlayerView(CurrentPlayer currentPlayer) {
    return getViewList()
                 .stream()
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().isCommunity())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().isPresent())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().get().equals(currentPlayer.getPlayer()))
                 .findFirst();
  }
  
  private PlayerView getUserPlayerView() {
    return this.getUserView().isPresent() ?
                 this.getUserView().get() :
                 (this.getUserDealerView().isPresent() ?
                        this.getUserDealerView().get() : null);
  }
  
  private Optional<UserView> getUserView() {
    return getViewList()
                 .stream()
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().isCommunity())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().isPresent())
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().getPlayer().get().isNPC())
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().getPlayer().get().isDealer())
                 .map(gameObjectView -> (UserView) gameObjectView)
                 .findFirst();
  }
  
  private Optional<DealerView> getUserDealerView() {
    return getViewList()
                 .stream()
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().isCommunity())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().isPresent())
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().getPlayer().get().isNPC())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().get().isDealer())
                 .map(gameObjectView -> (DealerView) gameObjectView)
                 .findFirst();
  }
  
  private List<NPCView> getNPCViews() {
    return getViewList()
                 .stream()
                 .filter(gameObjectView -> !gameObjectView.getCardsView().getCards().isCommunity())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().isPresent())
                 .filter(gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().get().isNPC())
                 .map(gameObjectView -> (NPCView) gameObjectView)
                 .collect(Collectors.toList());
  }
  
  private GameController getGameController() {
    return this.gameController;
  }
  
  @Override
  public void setGameController(GameController gameController) {
    this.gameController = gameController;
  }
  
  private LayoutBuilder getLayoutBuilder() {
    return this.layoutBuilder;
  }
  
  @Override
  public void setLayoutBuilder(LayoutBuilder layoutBuilder) {
    this.layoutBuilder = layoutBuilder;
  }
  
  @Override
  public void npcExchangeCards(Player currentPlayer) {
    getNPCViews()
          .stream()
          .filter(npcView -> npcView.getPlayer().equals(currentPlayer))
          .findFirst()
          .ifPresent(npcView -> {
            final Cards boardCards = getBoardView().getCardsView().getCards();
            final Cards npcCards = npcView.getCardsView().getCards();
            npcView.getPlayerController().exchangeCards(currentPlayer, boardCards, npcCards);
          });
  }
  
  private CardsViewFactory getCardsViewFactory() {
    return this.cardsViewFactory;
  }
  
  private GameObjectViewFactory getGameObjectViewFactory() {
    return this.gameObjectViewFactory;
  }
}
