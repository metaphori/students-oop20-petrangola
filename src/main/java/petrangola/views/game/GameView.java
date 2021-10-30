package petrangola.views.game;

import petrangola.controllers.player.DealerController;
import petrangola.controllers.player.PlayerController;
import petrangola.models.option.Option;
import petrangola.views.ControllableView;
import petrangola.views.cards.CardsViewFactory;
import petrangola.views.mediator.MediatorsFactory;

public interface GameView extends ControllableView {
  
  /**
   * @param dealerController
   */
  void setDealerController(DealerController dealerController);
  
  /**
   * @param playerController
   */
  void setPlayerController(PlayerController playerController);
  
  /**
   * @param cardsViewFactory
   */
  void setCardsViewFactory(CardsViewFactory cardsViewFactory);
  
  /**
   * @param mediatorsFactory
   */
  void setMediatorsFactory(MediatorsFactory mediatorsFactory);
  
  /**
   *
   * @param option
   */
  void setOption(Option option);
}
