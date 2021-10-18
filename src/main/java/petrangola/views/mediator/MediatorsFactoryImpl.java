package main.java.petrangola.views.mediator;

import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.GameObjectViewFactory;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.components.layout.LayoutBuilder;

import java.util.List;

public class MediatorsFactoryImpl implements MediatorsFactory {
  @Override
  public CardsMediator createCardsMediator(GameObjectViewFactory gameObjectViewFactory, CardsViewFactory cardsViewFactory, List<Cards> cardsList, List<PlayerDetail> playerDetails) {
    return new CardsMediatorImpl(gameObjectViewFactory, cardsViewFactory, cardsList, playerDetails);
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
