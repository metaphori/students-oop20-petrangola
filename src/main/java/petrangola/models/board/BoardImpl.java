package main.java.petrangola.models.board;

public class BoardImpl implements Board {
  private static final String OBJECT_ID = "board";
  
  @Override
  public String getObjectId() {
    return OBJECT_ID;
  }
}
