package main.java.petrangola.models.player;

import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.models.game.GameObject;

public interface Player extends Exchangeable, GameObject, ObservableModel {
  
  String getUsername();
  
  boolean isNPC();
  
  boolean isDealer();
 
  void setIsDealer(boolean isDealer);
}
