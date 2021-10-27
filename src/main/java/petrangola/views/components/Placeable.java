package main.java.petrangola.views.components;

import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public interface Placeable {
  /**
   * @return
   */
  Pair<Vertical, Horizontal> getPosition();
  
  /**
   * @param position
   */
  void setPosition(Pair<Vertical, Horizontal> position);
}
