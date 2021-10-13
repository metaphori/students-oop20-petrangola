package main.java.petrangola.views.mediator;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.services.ResourceService;
import main.java.petrangola.services.ResourceServiceImpl;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardViewImpl;
import main.java.petrangola.views.game.GameStyleClass;

import java.util.List;
import java.util.stream.Collectors;

public class HighCardMediatorImpl implements HighCardMediator {
  private static final int THRESHOLD_NPC = 6;
  final ResourceService resourceService = new ResourceServiceImpl();
  private final List<PlayerDetail> playersDetails;
  private int npcIndex = 0;
  
  
  public HighCardMediatorImpl(List<PlayerDetail> playersDetails) {
    this.playersDetails = playersDetails;
  }
  
  @Override
  public void register(Pane layout) {
    final List<FlowPane> npcPanes = getNPCHighCardPanes(layout);
    final Pane userPane = getUserHighCardPane(layout);
    
    this.playersDetails
          .stream()
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
            
            pane.getChildren().add(cardView.get());
          });
  }
  
  @Override
  public void unregister(Pane layout) {
    getNPCHighCardPanes(layout).forEach(pane -> pane.getChildren().clear());
    getUserHighCardPane(layout).getChildren().remove(0);
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
}
