package main.java.petrangola.models.cards;

import main.java.petrangola.utlis.Name;
import main.java.petrangola.utlis.Suit;

/**
 * The single Card model
 */
public interface Card {
  /**
   *
   * @return the name associated with the value
   */
  Name getName();
  
  /**
   *
   * @return the full name of a card , i.e. bastoni_7
   */
  String getFullName();
  
  /**
   *
   * @return the suit name
   */
  Suit getSuit();
  
  /**
   *
   * @return the associated value
   */
  int getValue();
  
  /**
   *
   * @param hidden
   */
  void setHidden(boolean hidden);
  
  /**
   *
   * @param covered
   */
  void setCovered(boolean covered);
  
}
