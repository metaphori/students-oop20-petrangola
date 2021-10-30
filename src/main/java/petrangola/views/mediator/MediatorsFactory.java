package petrangola.views.mediator;

import petrangola.controllers.game.GameController;
import petrangola.controllers.player.DealerController;
import petrangola.models.cards.Cards;
import petrangola.models.player.PlayerDetail;
import petrangola.views.GameObjectViewFactory;
import petrangola.views.cards.CardsViewFactory;
import petrangola.views.components.layout.LayoutBuilder;

import java.util.List;

public interface MediatorsFactory {
  
  CardsMediator createCardsMediator(GameObjectViewFactory gameObjectViewFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playersDetails);
  
  GameMediator createGameMediator(LayoutBuilder layoutBuilder, GameController gameController, DealerController dealerController);
  
  HighCardMediator createHighCardMediator();
  
}
