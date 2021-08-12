package main.java.petrangola.models.option;

import java.beans.PropertyChangeSupport;
import main.java.petrangola.utlis.DifficultyLevel;

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
    final int oldValue = this.opponentSize;
    this.opponentSize = opponentSize;
    firePropertyChange("opponentSize", oldValue, opponentSize);
  }
  
  @Override
  public DifficultyLevel getDifficultyLevel() {
    return this.difficultyLevel;
  }
  
  public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
    final DifficultyLevel oldValue = this.difficultyLevel;
    this.difficultyLevel = difficultyLevel;
    firePropertyChange("difficultyLevel", oldValue, difficultyLevel);
  }
  
  @Override
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    String oldValue = this.username;
    this.username = username;
    firePropertyChange("username", oldValue, username);
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
