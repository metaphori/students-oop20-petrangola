package petrangola.models.board;

public class BoardImpl implements Board {
  private final static String OBJECT_ID = "board";
  
  @Override
  public String getObjectId() {
    return OBJECT_ID;
  }
}
