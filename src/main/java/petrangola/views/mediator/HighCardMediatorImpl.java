package petrangola.views.mediator;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import petrangola.models.player.PlayerDetail;
import petrangola.services.ResourceService;
import petrangola.services.ResourceServiceImpl;
import petrangola.utlis.Pair;
import petrangola.views.ViewFX;
import petrangola.views.cards.CardView;
import petrangola.views.cards.CardViewImpl;
import petrangola.views.game.GameStyleClass;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HighCardMediatorImpl implements HighCardMediator {
  private static final int THRESHOLD_NPC = 6;
  private final ResourceService resourceService = new ResourceServiceImpl();
  private List<PlayerDetail> playersDetails;
  private int npcIndex = 0;
  
  
  public HighCardMediatorImpl() {
  }
  
  @Override
  public void register(Pane layout) {
    final List<FlowPane> npcPanes = getNPCHighCardPanes(layout);
    final Pane userPane = getUserHighCardPane(layout);
    
    Pane userCardsPane = (Pane) layout.lookup(GameStyleClass.USER_CARDS.getAsStyleClass());
    userCardsPane.setManaged(false);
    userCardsPane.setVisible(false);
    
    this.playersDetails
          .stream()
          .filter(PlayerDetail::isStillAlive)
          .map(playerDetail -> new Pair<>(playerDetail.getPlayer(), playerDetail.getHighCard()))
          .forEachOrdered(pair -> {
            final Pane pane;
            final CardView cardView = new CardViewImpl(pair.getY(), resourceService);
            
            if (pair.getX().isNPC()) {
              pane = this.npcIndex > THRESHOLD_NPC ? npcPanes.get(1) : npcPanes.get(0);
              this.npcIndex++;
            } else {
              pane = userPane;
            }
            
            ViewFX.addOrUpdate(pane, cardView.get());
          });
  }
  
  @Override
  public void unregister(Pane layout) {
    getNPCHighCardPanes(layout).forEach(pane -> pane.getChildren().clear());
    getUserHighCardPane(layout).getChildren().removeIf(Objects::nonNull);
  }
  
  @Override
  public List<FlowPane> getNPCHighCardPanes(Pane layout) {
    return layout.lookupAll(GameStyleClass.NPC_HIGH_CARD.getAsStyleClass())
                 .stream()
                 .map(node -> (FlowPane) node)
                 .collect(Collectors.toList());
  }
  
  @Override
  public Pane getUserHighCardPane(Pane layout) {
    return (Pane) layout.lookup(GameStyleClass.USER_HIGH_CARD.getAsStyleClass());
  }
  
  @Override
  public void setPlayersDetails(List<PlayerDetail> playersDetails) {
    this.playersDetails = playersDetails;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof HighCardMediatorImpl)) return false;
    HighCardMediatorImpl that = (HighCardMediatorImpl) o;
    return npcIndex == that.npcIndex && resourceService.equals(that.resourceService) && Objects.equals(playersDetails, that.playersDetails);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(resourceService, playersDetails, npcIndex);
  }
}
