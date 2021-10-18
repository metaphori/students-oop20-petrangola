package main.java.petrangola.views.player;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;
import main.java.petrangola.views.components.layout.LayoutBuilder;
import main.java.petrangola.views.game.GameStyleClass;

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
    sidePanes.get(index).getChildren().add(this.getCardsView().get());
  }
}
