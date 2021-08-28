package main.java.petrangola.views.player;

import javafx.scene.Group;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.player.buttons.AcceptDealtCardsButton;
import main.java.petrangola.views.player.buttons.FirstExchangeButton;

public class DealerViewImpl extends AbstractPlayerViewImpl implements DealerView {
  private AbstractButtonFX acceptDealtCardsButton;
  private AbstractButtonFX firstExchangeButton;
  
  public DealerViewImpl(final PlayerController playerController, Game game, final PlayerDetail playerDetail, final CardsView<Group> cardsView) {
    super(playerController, game, playerDetail, cardsView);
  }
  
  @Override
  public void init(GameController gameController, DealerController dealerController, Cards boardCards) {
    this.acceptDealtCardsButton = new AcceptDealtCardsButton(gameController);
    this.acceptDealtCardsButton.get().setVisible(true);
    
    this.firstExchangeButton = new FirstExchangeButton(dealerController, boardCards, cardsView.getCards());
    this.firstExchangeButton.get().setVisible(true);
  
    if (game.getRound() > 0) {
      this.acceptDealtCardsButton.get().setVisible(false);
      this.firstExchangeButton.get().setVisible(false);
    }
  }
  
  @Override
  public AbstractButtonFX getAcceptDealtCardsButton() {
    return this.acceptDealtCardsButton;
  }
  
  @Override
  public AbstractButtonFX getFirstExchangeButton() {
    return this.firstExchangeButton;
  }
}
