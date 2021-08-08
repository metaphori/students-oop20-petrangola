package main.java.petrangola.utlis;

public enum DeckConstants {
  DECK_SIZE(3);
  
  private final int value;
  
  DeckConstants(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
}
