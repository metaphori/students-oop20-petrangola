package petrangola.views.components;

import petrangola.utlis.Pair;
import petrangola.utlis.position.Horizontal;
import petrangola.utlis.position.Vertical;

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
