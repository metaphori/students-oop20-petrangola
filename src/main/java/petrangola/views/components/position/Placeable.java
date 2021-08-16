package main.java.petrangola.views.components.position;

import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public interface Placeable {
  /**
   * @param position
   */
  void setPosition(final Pair<Vertical, Horizontal> position);
  
  /**
   *
   * @return
   */
  Pair<Vertical, Horizontal> getPosition();
  
  
  /**
   *
   * @param depth
   */
  void setDepth(int depth);
  
  /**
   *
   * @return
   */
  int getDepth();
}
