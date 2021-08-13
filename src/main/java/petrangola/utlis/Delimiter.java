package main.java.petrangola.utlis;

public enum Delimiter {
  UNDERSCORE("_");
  
  private final String text;
  
  Delimiter(final String text) {
    this.text = text;
  }
  
  public String getText() {
    return text;
  }
}
