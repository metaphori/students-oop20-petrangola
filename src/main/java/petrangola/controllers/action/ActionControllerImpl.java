package main.java.petrangola.controllers.action;


import main.java.petrangola.views.ViewFactory;
import main.java.petrangola.views.ViewFactoryImpl;

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
}
