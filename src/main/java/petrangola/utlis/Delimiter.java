package petrangola.utlis;

public enum Delimiter {
  UNDERSCORE("_"),
  COMMA(",");
  
  private final String text;
  
  Delimiter(final String text) {
    this.text = text;
  }
  
  public String getText() {
    return text;
  }
}
