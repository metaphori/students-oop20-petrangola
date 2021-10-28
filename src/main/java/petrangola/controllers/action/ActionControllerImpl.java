package main.java.petrangola.controllers.action;


import main.java.petrangola.models.ObservableModel;
import main.java.petrangola.views.ViewFactory;

import java.util.Objects;

public class ActionControllerImpl implements ActionController {
  private ViewFactory viewFactory;
  
  public ActionControllerImpl() {
  }
  
  @Override
  public void start() {
    this.getViewFactory().createOptionView();
  }
  
  @Override
  public void quit() {
    System.exit(0);
  }
  
  private ViewFactory getViewFactory() {
    return this.viewFactory;
  }
  
  @Override
  public void setViewFactory(ViewFactory viewFactory) {
    this.viewFactory = viewFactory;
  }
  
  @Override
  public void setModel(ObservableModel model) {
    // actionController has no model
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ActionControllerImpl)) return false;
    ActionControllerImpl that = (ActionControllerImpl) o;
    return Objects.equals(getViewFactory(), that.getViewFactory());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getViewFactory());
  }
}
