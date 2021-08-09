package main.java.petrangola.views;

import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.views.components.ViewNode;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface View extends PropertyChangeListener {
  /**
   * Using the JavaBeans package this method can make a concrete View class an Observer
   * @param model - associated with the view
   */
  default void addListenerToModel(ObservableModel model) {
    model.addPropertyChangeListener(this);
  }
}
