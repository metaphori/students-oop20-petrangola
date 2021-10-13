package main.java.petrangola.views.player.animation;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public interface PlayerAnimation {
  /**
   *
   * @param millis
   * @param event
   */
  PlayerAnimation addKeyFrame(Duration millis, EventHandler<ActionEvent> event);
  
  PlayerAnimation play();
  
  PlayerAnimation pause();
  
  Timeline getTimeline();
  
}
