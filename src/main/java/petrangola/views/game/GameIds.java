package main.java.petrangola.views.game;

public enum GameIds {
  SIDES_IDS ("npcCards,highCard"),
  TOP_IDS ("life,round,username,dealer,knocks"),
  CENTRAL_IDS ("boardCards"),
  BOTTOM_IDS ("userCards,userActions,highCard");
  
  private final String ids;
  
  GameIds(String ids) {
    this.ids = ids;
  }
  
  public String getIds() {
    return ids;
  }
}
