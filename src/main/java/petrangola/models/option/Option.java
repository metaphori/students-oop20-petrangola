package petrangola.models.option;

import petrangola.dto.DTO;
import petrangola.models.ObservableModel;
import petrangola.utlis.DifficultyLevel;

public interface Option extends ObservableModel, DTO {
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
