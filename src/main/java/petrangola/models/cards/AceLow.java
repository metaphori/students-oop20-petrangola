package petrangola.models.cards;

import petrangola.utlis.Name;
import petrangola.utlis.Suit;

public class AceLow extends CardImpl {
  public AceLow(Name name, Suit suit) {
    super(name, suit);
  }
  
  @Override
  public int getValue() {
    return 1;
  }
}
