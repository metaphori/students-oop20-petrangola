package petrangola.models;

import petrangola.enums.DifficultyLevel;

public interface NPC extends Player {
  DifficultyLevel getDifficultyLevel();
}
