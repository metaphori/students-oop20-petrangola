package main.java.petrangola.views.mediator;

public interface UpdatableMediator extends Mediator {
  /**
   * @param propertyName
   * @param newValue
   */
  void update(String propertyName, Object newValue);
}
