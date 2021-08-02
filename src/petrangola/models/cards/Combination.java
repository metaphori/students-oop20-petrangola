package petrangola.models.cards;


import java.util.List;

public interface Combination {
  
  /**
   *
   * @return
   */
  boolean isTris();
  
  /**
   *
   * @return
   */
  boolean isFlush();
  
  /**
   *
   * @return
   */
  List<Card> getBest();
  
  /**
   *
   * @return
   */
  List<Card> getCards();
  
}
