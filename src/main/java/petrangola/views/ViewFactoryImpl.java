package petrangola.views;

import javafx.stage.Stage;
import petrangola.controllers.action.ActionController;
import petrangola.controllers.action.ActionControllerImpl;
import petrangola.controllers.game.GameController;
import petrangola.controllers.game.GameControllerImpl;
import petrangola.controllers.option.OptionController;
import petrangola.controllers.option.OptionControllerImpl;
import petrangola.controllers.player.DealerController;
import petrangola.controllers.player.DealerControllerImpl;
import petrangola.controllers.player.PlayerController;
import petrangola.controllers.player.PlayerControllerImpl;
import petrangola.models.option.Option;
import petrangola.views.action.ActionView;
import petrangola.views.action.ActionViewImpl;
import petrangola.views.cards.CardsViewFactory;
import petrangola.views.cards.CardsViewFactoryImpl;
import petrangola.views.game.GameView;
import petrangola.views.game.GameViewImpl;
import petrangola.views.mediator.MediatorsFactory;
import petrangola.views.mediator.MediatorsFactoryImpl;
import petrangola.views.option.OptionView;
import petrangola.views.option.OptionViewImpl;

public class ViewFactoryImpl implements ViewFactory {
  private static Stage STAGE;
  
  public ViewFactoryImpl(Stage stage) {
    STAGE = stage;
  }
  
  public static Stage getStage() {
    return STAGE;
  }
  
  @Override
  public void createGameView(final Option option) {
    GameView gameView = new GameViewImpl(getStage());
    GameController gameController = new GameControllerImpl();
    DealerController dealerController = new DealerControllerImpl();
    PlayerController playerController = new PlayerControllerImpl();
    CardsViewFactory cardsViewFactory = new CardsViewFactoryImpl();
    MediatorsFactory mediatorsFactory = new MediatorsFactoryImpl();
    
    gameView.setOption(option);
    gameView.setController(gameController);
    gameView.setDealerController(dealerController);
    gameView.setPlayerController(playerController);
    gameView.setCardsViewFactory(cardsViewFactory);
    gameView.setMediatorsFactory(mediatorsFactory);
    gameView.initView(this);
  }
  
  @Override
  public void createOptionView() {
    OptionController optionController = new OptionControllerImpl();
    OptionView optionView = new OptionViewImpl(getStage());
    
    optionView.setController(optionController);
    optionView.initView(this);
  }
  
  @Override
  public void createActionView() {
    ActionController actionController = new ActionControllerImpl();
    ActionView actionView = new ActionViewImpl(getStage());
    
    actionView.setController(actionController);
    actionView.initView(this);
  }
}
