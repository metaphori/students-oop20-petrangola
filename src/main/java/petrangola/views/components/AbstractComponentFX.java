package petrangola.views.components;


public abstract class AbstractComponentFX<E> extends ViewNodeImpl<E> {
  public AbstractComponentFX(E component) {
    super(component);
  }
  
  public abstract void setListeners();
  
}
