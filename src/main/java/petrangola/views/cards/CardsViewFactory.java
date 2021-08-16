package main.java.petrangola.views.cards;


public interface CardsViewFactory {
  /**
   * @return
   */
  CardsView createUserCards();
  
  /**
   * @return
   */
  CardsView createOpponentsCards();
  
  /**
   * @return
   */
  CardsView createBoardCards();
  
  /**
   * @return
   */
  CardsView createDealerCards(); // WHEN THE DEALER IS NOT THE USER ?
  
}
