package main.java.petrangola.views.events;

import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.PlayerDetail;

import java.util.List;

public class WinnerEvent implements Event {
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playerDetails;
  private final Pane layout;
  
  public WinnerEvent(final List<Cards> cardsList, List<PlayerDetail> playerDetails, Pane layout) {
    this.cardsList = cardsList;
    this.playerDetails = playerDetails;
    this.layout = layout;
  }
  
  public List<Cards> getCardsList() {
    return this.cardsList;
  }
  
  public List<PlayerDetail> getPlayerDetails() {
    return this.playerDetails;
  }
  
  public Pane getLayout() {
    return this.layout;
  }
}
