package petrangola.models.player;

import petrangola.models.ObservableModel;
import petrangola.models.game.GameObject;

public interface Player extends Exchanger, GameObject, ObservableModel {
  
  String getUsername();
  
  boolean isNPC();
  
  boolean isDealer();
 
  void setIsDealer(boolean isDealer);
}
