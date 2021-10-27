package main.java.petrangola.controllers.action;


import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.ViewFactoryImpl;

import java.util.Objects;

public class ActionControllerImpl implements ActionController {
  private final ViewFactory viewFactory;
  
  public ActionControllerImpl() {
    this.viewFactory = new ViewFactoryImpl(ViewFactoryImpl.getStage());
  }
  
  @Override
  public void start() {
    this.viewFactory.createOptionView();
  }
  
  @Override
  public void quit() {
    System.exit(0);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ActionControllerImpl)) return false;
    ActionControllerImpl that = (ActionControllerImpl) o;
    return viewFactory.equals(that.viewFactory);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(viewFactory);
  }
}
