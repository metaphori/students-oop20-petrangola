package petrangola.models.player;

import petrangola.models.game.GameObject;

public interface Player extends Exchangeable, GameObject {
  
  String getUsername();
  
  boolean isNPC();
  
}
