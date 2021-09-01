package main.java.petrangola.views.game;

public interface RankedPlayer {
  /**
   *
   * @return
   */
  String getUsername();
  
  /**
   *
   * @return
   */
  String getCards();
  
  /**
   *
   * @param username
   */
  void setUsername(String username);
  
  /**
   *
   * @param cards
   */
  void setCards(String cards);
  
  /**
   *
   * @return
   */
  int getCombinationValue();
  
  /**
   *
    * @param combinationValue
   */
  void setCombinationValue(int combinationValue);
  
  /**
   *
    * @return
   */
  boolean isPetrangola();
  
  /**
   *
    * @param isPetrangola
   */
  void setIsPetrangola(boolean isPetrangola);
  
  
}
