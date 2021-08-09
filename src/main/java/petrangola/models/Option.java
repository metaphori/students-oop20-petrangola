package main.java.petrangola.models;

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
}
