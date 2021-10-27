package main.java.petrangola.views.animation;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import main.java.petrangola.views.events.Event;

public interface Animation {
  /**
   * @param millis
   * @return
   */
  Animation waitForTaskAndLaunchEvents(int millis, Event... events);
  
  /**
   * @param millis
   * @param event
   */
  Animation addKeyFrame(Duration millis, EventHandler<ActionEvent> event);
  
  /**
   * @return
   */
  void play();
  
  /**
   * @return
   */
  Timeline getTimeline();
  
}
