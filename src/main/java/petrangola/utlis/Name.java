package petrangola.utlis;

public enum Name {
  ASSO(11),
  DUE(2),
  TRE(3),
  QUATTRO(4),
  CINQUE(5),
  SEI(6),
  SETTE(7),
  FANTE(10),
  CAVALLO(10),
  RE(10);
  
  private final int value;
  
  Name(final int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
}
