package petrangola.views.mediator;

import petrangola.controllers.game.GameController;
import petrangola.controllers.player.DealerController;
import petrangola.models.cards.Cards;
import petrangola.models.player.PlayerDetail;
import petrangola.views.GameObjectViewFactory;
import petrangola.views.cards.CardsViewFactory;
import petrangola.views.components.layout.LayoutBuilder;

import java.util.List;

public class MediatorsFactoryImpl implements MediatorsFactory {
  @Override
  public CardsMediator createCardsMediator(GameObjectViewFactory gameObjectViewFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playersDetails) {
    return new CardsMediatorImpl(gameObjectViewFactory, cardsViewFactory, cardsList, playersDetails);
  }
  
  @Override
  public GameMediator createGameMediator(LayoutBuilder layoutBuilder, GameController gameController, DealerController dealerController) {
    return new GameMediatorImpl(layoutBuilder, gameController, dealerController);
  }
  
  @Override
  public HighCardMediator createHighCardMediator() {
    return new HighCardMediatorImpl();
  }
}
