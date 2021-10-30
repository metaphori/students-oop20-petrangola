package petrangola.models.cards;

import petrangola.models.ObservableModel;
import petrangola.utlis.Name;
import petrangola.utlis.Suit;

/**
 * The single Card model
 */
public interface Card extends ObservableModel {
  /**
   * @return the name associated with the value
   */
  Name getName();
  
  /**
   * @return the full name of a card , i.e. bastoni_7
   */
  String getFullName();
  
  /**
   * @return the suit name
   */
  Suit getSuit();
  
  /**
   * @return the associated value
   */
  int getValue();
  
  /**
   * @return
   */
  boolean isHidden();
  
  /**
   * @param hidden
   */
  void setHidden(boolean hidden);
  
  /**
   * @return
   */
  boolean isCovered();
  
  /**
   * @param covered
   */
  void setCovered(boolean covered);
  
  /**
   *
   * @return
   */
  boolean isChosen();
  
  /**
   *
   * @param chosen
   */
  void setChosen(boolean chosen);
}
