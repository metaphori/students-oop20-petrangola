package main.java.petrangola.views.components;

import main.java.petrangola.utlis.Pair;
import main.java.petrangola.utlis.position.Horizontal;
import main.java.petrangola.utlis.position.Vertical;

public class ViewNodeImpl<E> implements ViewNode<E> {
  private final E node;
  private Pair<Vertical, Horizontal> position;
  
  public ViewNodeImpl(final E node) {
    this.node = node;
  }
  
  @Override
  public Pair<Vertical, Horizontal> getPosition() {
    return this.position;
  }
  
  @Override
  public void setPosition(Pair<Vertical, Horizontal> position) {
    this.position = position;
  }
  
  @Override
  public E get() {
    return this.node;
  }
}
