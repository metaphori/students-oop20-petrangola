package main.java.petrangola.models.option;

import main.java.petrangola.models.Option;
import main.java.petrangola.utlis.DifficultyLevel;

import java.beans.PropertyChangeSupport;

public class OptionImpl implements Option {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  
  private int opponentSize;
  private DifficultyLevel difficultyLevel;
  private String username;
  
  public OptionImpl() {}
  
  @Override
  public int getOpponentsSize() {
    return this.opponentSize;
  }
  
  public void setOpponentSize(int opponentSize) {
    this.opponentSize = opponentSize;
  }
  
  @Override
  public DifficultyLevel getDifficultyLevel() {
    return null;
  }
  
  public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }
  
  @Override
  public String getUsername() {
    return null;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return null;
  }
}
