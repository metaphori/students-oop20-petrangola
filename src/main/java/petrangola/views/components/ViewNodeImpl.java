package petrangola.views.components;

import petrangola.utlis.Pair;
import petrangola.utlis.position.Horizontal;
import petrangola.utlis.position.Vertical;

import java.util.Objects;

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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ViewNodeImpl)) return false;
    ViewNodeImpl<?> viewNode = (ViewNodeImpl<?>) o;
    return node.equals(viewNode.node) && getPosition().equals(viewNode.getPosition());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(node, getPosition());
  }
}
