package main.java.petrangola.views.animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import main.java.petrangola.views.events.Event;
import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;

public class AbstractAnimation implements Animation {
  private final Timeline timeline = new Timeline();
  
  @Override
  public Animation waitForTaskAndLaunchEvents(int millis, Event... events) {
    Task<Void> sleeper = new Task<>() {
      @Override
      protected Void call() {
        try {
          Thread.sleep(millis);
        } catch (InterruptedException e) {
          System.out.println(e.getMessage());
        }
        
        return null;
      }
    };
    
    sleeper.setOnSucceeded(event -> {
      Arrays.stream(events).forEachOrdered(gameEvent -> {
        EventBus.getDefault().post(gameEvent);
      });
    });
    
    new Thread(sleeper).start();
    
    return this;
  }
  
  @Override
  public Animation addKeyFrame(Duration millis, EventHandler<ActionEvent> event) {
    final KeyFrame keyFrame = new KeyFrame(millis, event);
    
    getTimeline().getKeyFrames().add(keyFrame);
    
    return this;
  }
  
  @Override
  public void play() {
    this.timeline.play();
  }
  
  @Override
  public Timeline getTimeline() {
    return this.timeline;
  }
}
