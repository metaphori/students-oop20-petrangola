package main.java.petrangola.views;

import main.java.petrangola.models.ObservableModel;

import java.beans.PropertyChangeListener;

public interface View extends PropertyChangeListener {
  /**
   * @param model
   * @return
   */
  default PropertyChangeListener[] getPropertyChangeListeners(ObservableModel model) {
    return model.getSupport().getPropertyChangeListeners();
  }
  
  default void removeListenerModelByProperty(ObservableModel model, String propertyName) {
    model.removePropertyChangeListener(propertyName, this);
  }
  
  /**
   * @param model
   */
  default void removeListenerModel(ObservableModel model) {
    model.removePropertyChangeListener(this);
  }
  
  /**
   * Using the JavaBeans package this method can make a concrete View class an Observer
   *
   * @param model - associated with the view
   */
  default void addListenerToModel(ObservableModel model) {
    model.addPropertyChangeListener(this);
  }
}
