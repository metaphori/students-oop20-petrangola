package main.java.petrangola.views.components.hierarchy;

public interface Children<C, P> {
  /**
   *
   * @param parent
   */
  void setParent(Parent<P> parent);
  
  /**
   *
   * @return
   */
  Parent<P> getParent();
}
