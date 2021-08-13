package main.java.petrangola.models.option;

import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.utlis.DifficultyLevel;

public interface Option extends ObservableModel {
  /**
   *
   * @return
   */
  int getOpponentsSize();
  
  /**
   *
   * @return
   */
  DifficultyLevel getDifficultyLevel();
  
  /**
   *
   * @return
   */
  String getUsername();
  
  /**
   *
   * @param opponentsSize
   */
  void setOpponentsSize(int opponentsSize);
  
  /**
   *
   * @param difficulty
   */
  void setDifficultyLevel(DifficultyLevel difficulty);
  
  /**
   *
   * @param username
   */
  void setUsername(String username);
}
