package main.java.petrangola.views.game;

import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.views.ControllableView;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.mediator.MediatorsFactory;

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
