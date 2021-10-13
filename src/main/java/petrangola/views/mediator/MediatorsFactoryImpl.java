package main.java.petrangola.views.mediator;

import main.java.petrangola.controllers.game.GameController;
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
  public DealerMediator createDealerMediator() {
    return new DealerMediatorImpl(null);
  }
  
  @Override
  public GameMediator createGameMediator(LayoutBuilder layoutBuilder, GameController gameController) {
    return new GameMediatorImpl(layoutBuilder, gameController);
  }
  
  @Override
  public PlayerMediator createPlayerMediator() {
    return null;
  }
  
  @Override
  public PlayerDetailMediator createPlayerDetailMediator() {
    return null;
  }
  
  @Override
  public HighCardMediator createHighCardMediator(List<PlayerDetail> playerDetails) {
    return new HighCardMediatorImpl(playerDetails);
  }
}
