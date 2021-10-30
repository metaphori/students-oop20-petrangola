package petrangola.models.option;

import java.beans.PropertyChangeSupport;
import java.util.Objects;

import petrangola.utlis.DifficultyLevel;

public class OptionImpl implements Option {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  
  private int opponentsSize = 1;
  private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
  private String username;
  
  public OptionImpl() {}
  
  @Override
  public int getOpponentsSize() {
    return this.opponentsSize;
  }
  
  @Override
  public void setOpponentsSize(int opponentsSize) {
    final int oldValue = this.opponentsSize;
    this.opponentsSize = opponentsSize;
    firePropertyChange("opponentsSize", oldValue, opponentsSize);
  }
  
  @Override
  public DifficultyLevel getDifficultyLevel() {
    return this.difficultyLevel;
  }
  
  @Override
  public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
    final DifficultyLevel oldValue = this.difficultyLevel;
    this.difficultyLevel = difficultyLevel;
    firePropertyChange("difficultyLevel", oldValue, difficultyLevel);
  }
  
  @Override
  public String getUsername() {
    return this.username;
  }
  
  @Override
  public void setUsername(String username) {
    String oldValue = this.username;
    this.username = username;
    firePropertyChange("username", oldValue, username);
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OptionImpl)) return false;
    OptionImpl option = (OptionImpl) o;
    return getOpponentsSize() == option.getOpponentsSize() && getSupport().equals(option.getSupport()) && getDifficultyLevel() == option.getDifficultyLevel() && getUsername().equals(option.getUsername());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getSupport(), getOpponentsSize(), getDifficultyLevel(), getUsername());
  }
}
