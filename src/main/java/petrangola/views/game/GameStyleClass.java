package main.java.petrangola.views.game;

import main.java.petrangola.utlis.Delimiter;

import java.util.List;
import java.util.stream.Collectors;

public enum GameStyleClass {
  NPC_CARDS("npcCards"),
  HIGH_CARD("highCard"),
  SIDES_IDS(List.of(NPC_CARDS, HIGH_CARD)),
  
  LIFE("life"),
  ROUND("round"),
  USERNAME("username"),
  DEALER("dealer"),
  KNOCKS("knocks"),
  TOP_IDS(List.of(LIFE, ROUND, USERNAME, DEALER, KNOCKS)),
  
  BOARD_CARDS("boardCards"),
  CENTRAL_IDS(List.of(BOARD_CARDS)),
  
  USER_CARDS("userCards"),
  USER_ACTIONS("userActions"),
  BOTTOM_IDS(List.of(USER_ACTIONS, USER_ACTIONS, HIGH_CARD));
  
  private final String classes;
  
  GameStyleClass(String classes) {
    this.classes = classes;
  }
  
  GameStyleClass(List<GameStyleClass> classes) {
    this.classes = classes.stream().map(GameStyleClass::getClasses).collect(Collectors.joining(Delimiter.COMMA.getText()));
  }
  
  public String getClasses() {
    return classes;
  }
}
