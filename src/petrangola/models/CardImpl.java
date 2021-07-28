package petrangola.models;

import petrangola.utlis.Delimiter;
import petrangola.utlis.Name;
import petrangola.utlis.Suit;

/**
 * {@inheritDoc}
 */
public class CardImpl implements Card {
  private final int value;
  private final String suit;
  
  public CardImpl(final Name name, final Suit suit) {
    this.value = name.getValue();
    this.suit = suit.toString().toLowerCase();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getFullName() {
    return getSuit()
                 .concat(Delimiter.UNDERSCORE.toString())
                 .concat(String.valueOf(getValue()));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getSuit() {
    return this.suit;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue() {
    return this.value;
  }
}
