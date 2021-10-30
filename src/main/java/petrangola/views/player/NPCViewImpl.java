package petrangola.views.player;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import petrangola.controllers.player.PlayerController;
import petrangola.models.game.Game;
import petrangola.models.player.PlayerDetail;
import petrangola.utlis.Pair;
import petrangola.utlis.position.Horizontal;
import petrangola.utlis.position.Vertical;
import petrangola.views.ViewFX;
import petrangola.views.components.layout.LayoutBuilder;
import petrangola.views.game.GameStyleClass;

import java.util.List;
import java.util.stream.Collectors;

public class NPCViewImpl extends AbstractPlayerViewImpl implements NPCView {
  public NPCViewImpl(final PlayerController playerController, final Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(playerController, game, playerDetail, layout);
  }
  
  @Override
  public void register(Pane layout, LayoutBuilder layoutBuilder) {
    final List<FlowPane> sidePanes = layout.lookupAll(GameStyleClass.NPC_CARDS.getAsStyleClass())
                                           .stream()
                                           .map(node -> (FlowPane) node)
                                           .collect(Collectors.toList());
    final Pair<Vertical, Horizontal> position = this.getCardsView().getPosition();
    final int index = position.getY().equals(Horizontal.RIGHT) ? 1 : 0;
    
    sidePanes.get(index).setAlignment(layoutBuilder.getPos(position));
    
    ViewFX.addOrUpdate(sidePanes.get(index), this.getCardsView().get());
  }
}
