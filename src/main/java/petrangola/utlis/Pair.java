package petrangola.utlis;

/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 * This class is taken from our OOP Course
 */

public class Pair<X, Y> {
  private final X x;
  private final Y y;
  
  public Pair(final X x, final Y y) {
    super();
    this.x = x;
    this.y = y;
  }
  
  public X getX() {
    return x;
  }
  
  public Y getY() {
    return y;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((x == null) ? 0 : x.hashCode());
    result = prime * result + ((y == null) ? 0 : y.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pair)) return false;
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return getX().equals(pair.getX()) && getY().equals(pair.getY());
  }
  
  @Override
  public String toString() {
    return "Pair [x=" + x + ", y=" + y + "]";
  }
}
