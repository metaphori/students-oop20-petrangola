package main.java.petrangola.models.cards;

import main.java.petrangola.utlis.Name;
import main.java.petrangola.utlis.Suit;

public class AceLow extends CardImpl {
  public AceLow(Name name, Suit suit) {
    super(name, suit);
  }
  
  @Override
  public int getValue() {
    return 1;
  }
}
