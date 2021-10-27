package main.java.petrangola.models.cards;

import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.Pair;

import java.util.List;

public class BestCombinationComparatorImpl implements BestCombinationsComparator {
  private static final Integer O1_GREATER = 1;
  private static final Integer O2_GREATER = -1;
  
  /**
   * Keep in mind that compare should return:
   * the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y
   *
   */
  @Override
  public int compare(Pair<List<Card>, Integer> o1, Pair<List<Card>, Integer> o2) {
    if (CombinationChecker.isFlush(o1.getX()) && CombinationChecker.isFlush(o2.getX())) {
      return Integer.compare(o1.getY(), o2.getY());
    }
  
    if (CombinationChecker.isTris(o1.getX()) && CombinationChecker.isTris(o2.getX())) {
      return Integer.compare(o1.getY(), o2.getY());
    }
    
    if (CombinationChecker.isAnyKindOfPetrangola(o1.getX()) && CombinationChecker.isAnyKindOfPetrangola(o2.getX())) {
      return Integer.compare(o1.getY(), o2.getY());
    }
    
    if (CombinationChecker.isAnyKindOfPetrangola(o1.getX())) {
      return O1_GREATER;
    }
    
    if (CombinationChecker.isAnyKindOfPetrangola(o2.getX())) {
      return O2_GREATER;
    }
  
    return Integer.compare(o1.getY(), o2.getY());
  }
}
