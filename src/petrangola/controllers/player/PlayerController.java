package petrangola.controllers.player;

import petrangola.controllers.Controller;

public interface PlayerController extends Controller {
  /**
   *
   */
  void exchangeCards();
  
  /**
   *
   */
  void knock();
  
  /**
   *
   */
  void looseLife();
  
  /**
   *
   */
  boolean checkIfStillAlive();
  
  
}
