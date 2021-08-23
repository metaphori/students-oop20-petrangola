package main.java.petrangola.utlis.position;

public enum Vertical implements Position {
  TOP,
  CENTER,
  BOTTOM;
  
  @Override
  public Position[] positions() {
    return Vertical.values();
  }
}
