package main.java.petrangola.views.mediator;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
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
import main.java.petrangola.views.player.CurrentPlayer;
import main.java.petrangola.views.player.GameObjectView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.PlayerView;

import java.util.List;
import java.util.Optional;

public class CardsMediatorImpl implements CardsMediator {
  private static final Pair<Vertical, Horizontal> BOARD_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
  private static final Pair<Vertical, Horizontal> USER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final Pair<Vertical, Horizontal> DEALER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.LEFT);
  private static final int THRESHOLD_NPC = 6;
  
  private final CardsExchanged cardsExchanged = new CardsExchangedImpl();
  
  private final GameObjectViewFactory gameObjectViewFactory;
  private final CardsViewFactory cardsViewFactory;
  
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playersDetails;
  
  private int npcIndex = 0;
  
  private List<GameObjectView> viewList;
  
  public CardsMediatorImpl(GameObjectViewFactory gameObjectViewFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playerDetails) {
    this.gameObjectViewFactory = gameObjectViewFactory;
    this.cardsViewFactory = cardsViewFactory;
    this.cardsList = cardsList;
    this.playersDetails = playerDetails;
    
    this.init();
  }
  
  private void init() {
    cardsList.stream()
          .filter(Cards::isCommunity)
          .findFirst()
          .ifPresent(cards -> {
            GameObjectView gameObjectView = gameObjectViewFactory.createBoardView();
            gameObjectView.setCardsView(this.cardsViewFactory.createBoardCards(cards, BOARD_POSITION));
            this.viewList.add(gameObjectView);
          });
    
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
                final GameObjectView gameObjectView;
                final CardsView<Group> cardsView;
                
                if (player.isNPC()) {
                  cardsView = this.cardsViewFactory.createOpponentCards(cards, npcIndex, THRESHOLD_NPC);
                  gameObjectView = gameObjectViewFactory.createNPCView(playerDetail);
                  npcIndex++;
                  
                } else if (player.isDealer()) {
                  cardsView = this.cardsViewFactory.createDealerCards(cards, DEALER_POSITION);
                  gameObjectView = gameObjectViewFactory.createDealerView(playerDetail);
                } else {
                  cardsView = this.cardsViewFactory.createUserCards(cards, USER_POSITION);
                  gameObjectView = gameObjectViewFactory.createUserView(playerDetail);
                }
                
                
                gameObjectView.setCardsView(cardsView);
                gameObjectView.setBoardView(getBoardView());
                gameObjectView.addListenerToModel(cardsView.getCards());
                gameObjectView.addListenerToModel(playerDetail);
                gameObjectView.addListenerToModel(player);
                
                viewList.add(gameObjectView);
              });
      });
    });
  }
  
  @Override
  public void onUpdatedCombination() {
  
  }
  
  @Override
  public void showBoardCards() {
    getBoardView().showCards();
  }
  
  private BoardView getBoardView() {
    return (BoardView) this.viewList.get(0);
  }
  
  @Override
  public void showNPCCards() {
    this.viewList
          .stream()
          .filter(gameObjectView -> gameObjectView.getClass().isInstance(NPCView.class))
          .forEach(GameObjectView::showCards);
  }
  
  @Override
  public void showUserCards() {
    this.viewList
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
    getPlayerView(currentPlayer).ifPresent(gameObjectView -> ((PlayerView) gameObjectView).toggleUserButton(currentPlayer.getPlayer()));
  }
  
  private Optional<GameObjectView> getPlayerView(CurrentPlayer currentPlayer) {
    return this.viewList
               .stream()
               .filter( gameObjectView -> gameObjectView.getCardsView().getCards().isCommunity())
               .filter( gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().equals(currentPlayer.getPlayer()))
               .findFirst();
  }
  
  @Override
  public void register(Pane layout) {
  
  }
  
  @Override
  public void unregister(Pane layout) {
  
  }
}
