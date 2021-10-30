package petrangola.utlis.position;

public enum Horizontal implements Position {
  LEFT,
  CENTER,
  RIGHT;
  
  @Override
  public Position[] positions() {
    return Horizontal.values();
  }
}