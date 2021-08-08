package main.java.petrangola.controllers.action;

import main.java.petrangola.controllers.Controller;

public interface ActionController extends Controller {
  
  void start();
  
  void play();
  
  void goTo();
  
  void goBack();
  
}
