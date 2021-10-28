package main.java.petrangola.views;

import javafx.stage.Stage;
import main.java.petrangola.controllers.action.ActionController;
import main.java.petrangola.controllers.action.ActionControllerImpl;
import main.java.petrangola.controllers.game.GameController;
import main.java.petrangola.controllers.game.GameControllerImpl;
import main.java.petrangola.controllers.option.OptionController;
import main.java.petrangola.controllers.option.OptionControllerImpl;
import main.java.petrangola.controllers.player.DealerController;
import main.java.petrangola.controllers.player.DealerControllerImpl;
import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.controllers.player.PlayerControllerImpl;
import main.java.petrangola.models.option.Option;
import main.java.petrangola.views.action.ActionView;
import main.java.petrangola.views.action.ActionViewImpl;
import main.java.petrangola.views.cards.CardsViewFactory;
import main.java.petrangola.views.cards.CardsViewFactoryImpl;
import main.java.petrangola.views.game.GameView;
import main.java.petrangola.views.game.GameViewImpl;
import main.java.petrangola.views.mediator.MediatorsFactory;
import main.java.petrangola.views.mediator.MediatorsFactoryImpl;
import main.java.petrangola.views.option.OptionView;
import main.java.petrangola.views.option.OptionViewImpl;

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
