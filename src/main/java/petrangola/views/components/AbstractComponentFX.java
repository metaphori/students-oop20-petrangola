package main.java.petrangola.views.components;

import main.java.petrangola.utlis.Delimiter;

public abstract class AbstractComponentFX<E> extends ViewNodeImpl<E> {
  public AbstractComponentFX(E component) {
    super(component);
  }
  
  public abstract void setListeners();
  
  public String getId() {
    /*return getPosition().getX().name()
                 .concat(Delimiter.UNDERSCORE.name())
                 .concat(getPosition().getY().name())
                 .concat(String.valueOf(getDepth()));*/
    
    return getClass().getSimpleName();
  };
}
