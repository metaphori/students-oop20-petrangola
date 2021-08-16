package main.java.petrangola.views;

import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.views.components.ViewNode;
import main.java.petrangola.views.option.OptionViewImpl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

public interface View extends PropertyChangeListener {
  /**
   * Using the JavaBeans package this method can make a concrete View class an Observer
   * @param model - associated with the view
   */
  default void addListenerToModel(ObservableModel model) {
    model.addPropertyChangeListener(this);
  }
  
  default void propertyChange(PropertyChangeEvent evt, View view) {
    Arrays.stream(view.getClass().getDeclaredFields()).forEach(field -> {
      if (field.getName().equals(evt.getPropertyName())) {
        try {
          field.setAccessible(true); // I really don't want to use this but it save me from duplication
          field.set(this, evt.getNewValue());
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
