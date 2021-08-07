package petrangola.controllers.action;

import petrangola.controllers.Controller;

public interface ActionController extends Controller {
  
  void start();
  
  void play();
  
  void goTo();
  
  void goBack();
  
}
