package main.java.petrangola.views.events;

import main.java.petrangola.controllers.game.GameController;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventManagerImpl implements EventManager {
  private final GameController gameController;
  
  public EventManagerImpl(final GameController gameController) {
    this.gameController = gameController;
  }
  
  @Subscribe
  public void onNextRound(NextRoundEvent event) {
    System.out.println("next round");
    this.gameController.roundHandler();
  }
  
  @Subscribe
  public void onNextTurn(NextTurnEvent event) {
    System.out.println("next turn");
    this.gameController.nextTurnNumberHandler();
  }
  
  @Override
  public void register() {
    EventBus.getDefault().register(this);
  }
  
  @Override
  public void cleanup() {
    EventBus.getDefault().unregister(this);
  }
}
