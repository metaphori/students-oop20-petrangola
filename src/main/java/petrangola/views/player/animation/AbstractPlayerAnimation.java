package main.java.petrangola.views.player.animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AbstractPlayerAnimation implements PlayerAnimation {
  private final Timeline timeline = new Timeline();
  
  @Override
  public PlayerAnimation addKeyFrame(Duration millis, EventHandler<ActionEvent> event) {
    final KeyFrame keyFrame = new KeyFrame(millis, event);
    
    getTimeline().getKeyFrames().add(keyFrame);
    
    return this;
  }
  
  @Override
  public PlayerAnimation play() {
    this.timeline.play();
    return this;
  }
  
  @Override
  public PlayerAnimation pause() {
    this.timeline.pause();
    return this;
  }
  
  @Override
  public Timeline getTimeline() {
    return this.timeline;
  }
  
  
}
