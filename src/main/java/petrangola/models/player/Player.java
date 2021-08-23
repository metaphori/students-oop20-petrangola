package main.java.petrangola.models.player;

import main.java.petrangola.models.game.GameObject;

public interface Player extends Exchangeable, GameObject {
  
  String getUsername();
  
  boolean isNPC();
  
  boolean isDealer();
  
}
