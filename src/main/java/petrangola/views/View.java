package main.java.petrangola.views;

import main.java.petrangola.models.ObservableModel;

import java.beans.PropertyChangeListener;

public interface View extends PropertyChangeListener {
  /**
   *
   * @param model
   */
  default void removeListenerModel(ObservableModel model) {
    model.removePropertyChangeLister(this);
  }
  
  /**
   * Using the JavaBeans package this method can make a concrete View class an Observer
   * @param model - associated with the view
   */
  default void addListenerToModel(ObservableModel model) {
    model.addPropertyChangeListener(this);
  }
}
