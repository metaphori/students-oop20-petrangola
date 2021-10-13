package main.java.petrangola.views.player;

import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.components.button.AbstractButtonFX;
import main.java.petrangola.views.mediator.HighCardMediator;
import main.java.petrangola.views.player.animation.DealerAnimation;
import main.java.petrangola.views.player.animation.DealerAnimationImpl;
import main.java.petrangola.views.player.buttons.AcceptDealtCardsButton;
import main.java.petrangola.views.player.buttons.TakeBoardCardsButton;

public class DealerViewImpl extends AbstractPlayerViewImpl implements DealerView {
  private final DealerAnimation dealerAnimation = new DealerAnimationImpl(getLayout());
  
  private HighCardMediator highCardMediator;
  private AbstractButtonFX acceptDealtCardsButton;
  private AbstractButtonFX firstExchangeButton;
  
  public DealerViewImpl(final DealerController dealerController, Game game, final PlayerDetail playerDetail, final Pane layout) {
    super(dealerController, game, playerDetail, layout);
  }
  
  @Override
  public void init(GameController gameController, DealerController dealerController, Cards boardCards, HighCardMediator highCardMediator) {
    this.acceptDealtCardsButton = new AcceptDealtCardsButton(gameController);
    this.highCardMediator = highCardMediator;
    this.firstExchangeButton = new TakeBoardCardsButton(gameController, dealerController, boardCards, getCardsView().getCards());
    
    this.acceptDealtCardsButton.get().setVisible(true);
    this.firstExchangeButton.get().setVisible(true);
    
    if (game.getRound() > 0) {
      this.acceptDealtCardsButton.get().setVisible(false);
      this.firstExchangeButton.get().setVisible(false);
    } else {
      afterDealerIsSetAnimation();
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
  
  
  private void afterDealerIsSetAnimation() {
    getDealerAnimation().setDealerController((DealerController) getPlayerController());
    
    getDealerAnimation()
          .addKeyFrame(Duration.millis(600), getDealerAnimation().showDealerName())
          .addKeyFrame(Duration.millis(2600), getDealerAnimation().hideHighCards(this.highCardMediator))
          .addKeyFrame(Duration.millis(3000), getDealerAnimation().dealCards());
  }
  
  
  private DealerAnimation getDealerAnimation() {
    return this.dealerAnimation;
  }
}
