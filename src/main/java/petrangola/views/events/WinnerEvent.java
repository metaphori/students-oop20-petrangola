package main.java.petrangola.views.events;

import javafx.scene.layout.Pane;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.PlayerDetail;

import java.util.List;

public class WinnerEvent implements Event {
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playerDetails;
  private final PlayerController playerController;
  private final Pane layout;
  
  public WinnerEvent(final List<Cards> cardsList, List<PlayerDetail> playerDetails, PlayerController playerController, Pane layout) {
    this.cardsList = cardsList;
    this.playerDetails = playerDetails;
    this.playerController = playerController;
    this.layout = layout;
  }
  
  public List<Cards> getCardsList() {
    return this.cardsList;
  }
  
  public List<PlayerDetail> getPlayerDetails() {
    return this.playerDetails;
  }
  
  public PlayerController getPlayerController() {
    return this.playerController;
  }
  
  public Pane getLayout() {
    return this.layout;
  }
}
