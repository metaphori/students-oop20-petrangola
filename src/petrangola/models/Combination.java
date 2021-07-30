package petrangola.models;


import java.util.List;

public interface Combination {
  
  boolean isTris();
  
  boolean isFlush();
  
  List<Card> getBest();
  
  List<Card> getCards();
  
}
