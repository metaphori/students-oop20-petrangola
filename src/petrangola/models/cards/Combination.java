package petrangola.models.cards;


import java.util.List;
import petrangola.utlis.Pair;

public interface Combination {
  /**
   *
   * @return if the cards have the same name value
   */
  boolean isTris();
  
  /**
   *
   * @return if the cards is have the same suit and are consecutive
   */
  boolean isFlush();
  
  /**
   *
   * @return true if the Ace card is in combination with 2 and 3, obviously it has to have the same suit
   */
  boolean isAceLow();
  
  /**
   *
   * @return the best combination of cards ( the cards with the bigger sum )
   */
  Pair<List<Card>, Integer> getBest();
  
  /**
   *
   * @return simply the list of cards
   */
  List<Card> getCards();
  
}
