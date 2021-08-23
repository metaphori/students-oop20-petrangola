package main.java.petrangola.views.mediator;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.ViewNodeFactory;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.game.GameIds;
import main.java.petrangola.views.player.DealerView;
import main.java.petrangola.views.player.GameObjectView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.UserView;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class GameObjectViewMediator implements Mediator {
  private static final Pair<Vertical, Horizontal> BOARD_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
  private static final Pair<Vertical, Horizontal> USER_POSITION = new Pair<>(Vertical.BOTTOM, Horizontal.CENTER);
  private static final Pair<Vertical, Horizontal> DEALER_POSITION = new Pair<>(Vertical.CENTER, Horizontal.CENTER);
  private static final int THRESHOLD_NPC = 6;
  
  private final ViewNodeFactory viewNodeFactory;
  private final CardsViewFactory cardsViewFactory;
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playersDetails;
  
  private int npcIndex = 0;
  
  private BoardView boardView;
  private NPCView npcView;
  private UserView userView;
  private DealerView dealerView;
  
  private enum GAME_OBJECT {
    USER,
    NPC,
    DEALER,
    BOARD
  }
  
  public GameObjectViewMediator(ViewNodeFactory viewNodeFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playersDetails) {
    this.viewNodeFactory = viewNodeFactory;
    this.cardsViewFactory = cardsViewFactory;
    this.cardsList = cardsList;
    this.playersDetails = playersDetails;
    
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
                this.npcView = viewNodeFactory.createNPCView(playerDetail, pair.getX());
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
  
  private void registerSides(Pane pane) {
    final Set<Pane> sidePanes = pane.lookupAll(GameIds.SIDES_IDS.getIds())
                                  .stream()
                                  .map(node -> (Pane) node)
                                  .collect(Collectors.toSet());
    
    
  }
  
  private void registerBottom(Pane pane) {
  }
  
  private void registerCentral(Pane pane) {
  }
  
  private void registerTop(Pane pane) {
  }
  
  private Pair<CardsView<List<CardView>>, GAME_OBJECT> getCardsViewMethod(final Cards cards) {
    if (cards.isCommunity()) {
      return new Pair<>(this.cardsViewFactory.createBoardCards(cards, BOARD_POSITION), GAME_OBJECT.BOARD);
    }
    
    final Optional<Player> player = cards.getPlayer();
    
    if (player.isEmpty()) {
      throw new IllegalStateException();
    }
  
    if (player.get().isDealer()) {
      return new Pair<>(this.cardsViewFactory.createDealerCards(cards, DEALER_POSITION), GAME_OBJECT.DEALER);
    }
    
    if (player.get().isNPC()) {
      this.npcIndex++;
      return new Pair<>(this.cardsViewFactory.createOpponentCards(cards, npcIndex, THRESHOLD_NPC), GAME_OBJECT.NPC);
    }
    
    return new Pair<>(this.cardsViewFactory.createUserCards(cards, USER_POSITION), GAME_OBJECT.USER);
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
}
