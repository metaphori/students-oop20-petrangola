package petrangola.views;

import petrangola.models.ObservableModel;

import java.beans.PropertyChangeListener;

public interface View extends PropertyChangeListener {
  /**
   * Using the JavaBeans package this method can make a concrete View class an Observer
   * @param model - associated with the view
   */
  default void addListenerToModel(ObservableModel model) {
    model.addPropertyChangeListener(this);
  }
}
