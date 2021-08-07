package petrangola.models.cards;

import petrangola.utlis.Delimiter;
import petrangola.utlis.Name;
import petrangola.utlis.Suit;

import java.util.Objects;

/**
 * {@inheritDoc}
 */
public class CardImpl implements Card {
  private final Name name;
  private final Suit suit;
  
  public CardImpl(final Name name, final Suit suit) {
    this.name = name;
    this.suit = suit;
  }
  
  @Override
  public Name getName() {
    return this.name;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String getFullName() {
    return getSuit().toString().toLowerCase()
                 .concat(Delimiter.UNDERSCORE.toString())
                 .concat(String.valueOf(getValue()));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Suit getSuit() {
    return this.suit;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue() {
    return getName().getValue();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardImpl)) return false;
    CardImpl card = (CardImpl) o;
    return getName() == card.getName() && getSuit() == card.getSuit();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getSuit());
  }
}
