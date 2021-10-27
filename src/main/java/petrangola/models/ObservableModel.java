package main.java.petrangola.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ObservableModel {
  /**
   *
   * @return
   */
  PropertyChangeSupport getSupport();
  
  /**
   *
   * @param propertyName
   * @param oldValue
   * @param newValue
   */
  default void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    System.out.println("property name: " + propertyName + "  oldValue: " + oldValue + "  newValue: " + newValue);
    getSupport().firePropertyChange(propertyName, oldValue, newValue);
  }
  
  /**
   *
   * @param listener
   */
  default void addPropertyChangeListener(PropertyChangeListener listener) {
    getSupport().addPropertyChangeListener(listener);
  }
  
  /**
   *
   * @param pcl
   */
  default void removePropertyChangeListener(PropertyChangeListener pcl) {
    getSupport().removePropertyChangeListener(pcl);
  }
  
  /**
   *
   * @param propertyName
   * @param listener
   */
  default void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    getSupport().removePropertyChangeListener(propertyName, listener);
  }
}
