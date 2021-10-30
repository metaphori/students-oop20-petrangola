package petrangola.views.animation.player;

import petrangola.models.cards.Cards;
import petrangola.models.player.Player;
import petrangola.views.animation.AbstractAnimation;
import petrangola.views.events.Event;
import petrangola.views.events.KnockEvent;
import petrangola.views.events.NextRoundEvent;
import petrangola.views.events.NextTurnEvent;
import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

public class PlayerAnimationImpl extends AbstractAnimation implements PlayerAnimation {
  public PlayerAnimationImpl() {
  }
  
  @Override
  public void onFirstExchange(Player player) {
    final Event[] events = new Event[]{new NextRoundEvent(), new NextTurnEvent()};
    
    if (player.isNPC()) {
      this.waitForTaskAndLaunchEvents(500, events);
    } else {
      this.launchEventsWithoutWaiting(events);
    }
  }
  
  @Override
  public void onExchange(Player player, List<Cards> cardsList) {
    final Event event = new NextTurnEvent();
    
    if (cardsList.isEmpty()) {
      this.waitForTaskAndLaunchEvents(1500, new KnockEvent(player));
      return;
    }
    
    if (player.isNPC()) {
      this.waitForTaskAndLaunchEvents(700, event);
    } else {
      this.launchEventsWithoutWaiting(event);
    }
  }
  
  private void launchEventsWithoutWaiting(Event... events) {
    Arrays.stream(events).forEach(event -> EventBus.getDefault().post(event));
  }
}
