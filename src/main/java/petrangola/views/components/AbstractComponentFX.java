package main.java.petrangola.views.components;

import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public abstract class AbstractComponentFX<E> implements ViewNode<E> {
  private final E component;
  private Pair<Vertical, Horizontal> position;
  private int depth;
  
  public AbstractComponentFX(E component) {
    this.component = component;
  }
  
  @Override
  public E get() {
    return this.component;
  }
  
  public abstract void setListeners();
  
  @Override
  public Pair<Vertical, Horizontal> getPosition() {
    return this.position;
  }
  
  @Override
  public void setPosition(Pair<Vertical, Horizontal> position) {
    this.position = position;
  }
  
  @Override
  public int getDepth() {
    return this.depth;
  }
  
  @Override
  public void setDepth(int depth) {
    this.depth = depth;
  }
}
