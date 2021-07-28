package petrangola.models;

/**
 * The single Card model
 */
public interface Card {
  
  /**
   *
   * @return the full name of a card , i.e. bastoni_7
   */
  String getFullName();
  
  /**
   *
   * @return the suit name
   */
  String getSuit();
  
  /**
   *
   * @return the associated value
   */
  int getValue();
  
}
