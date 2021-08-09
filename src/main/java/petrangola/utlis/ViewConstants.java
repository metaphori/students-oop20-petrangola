package main.java.petrangola.utlis;

public enum ViewConstants {
  WIDTH(720),
  HEIGHT(480);
  
  private final int length;
  
  ViewConstants(final int length) {
    this.length = length;
  }
  
  public int getLength() {
    return this.length;
  }
}
