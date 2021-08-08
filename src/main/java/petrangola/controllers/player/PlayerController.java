package main.java.petrangola.controllers.player;

import main.java.petrangola.controllers.Controller;

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
