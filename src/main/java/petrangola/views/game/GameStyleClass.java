package main.java.petrangola.views.game;

import main.java.petrangola.utlis.Delimiter;

import java.util.List;
import java.util.stream.Collectors;

public enum GameStyleClass {
  NPC_HIGH_CARD("npcHighCard"),
  NPC_CARDS("npcCards"),
  SIDES_IDS(List.of(NPC_CARDS, NPC_HIGH_CARD)),
  
  LIFE("life"),
  ROUND("round"),
  USERNAME("username"),
  DEALER("dealer"),
  KNOCKS("knocks"),
  TOP_IDS(List.of(LIFE, ROUND, USERNAME, DEALER, KNOCKS)),
  
  RANKING("ranking"),
  BOARD_CARDS("boardCards"),
  CENTRAL_IDS(List.of(BOARD_CARDS, RANKING)),
  
  USER_CARDS("userCards"),
  USER_ACTIONS("userActions"),
  USER_HIGH_CARD("userHighCard"),
  DEALER_BUTTONS("dealerButtons"),
  BOTTOM_IDS(List.of(USER_CARDS, USER_ACTIONS, USER_HIGH_CARD, DEALER_BUTTONS));
  
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
  
  public String getAsStyleClass() {
    return this.classes.contains(Delimiter.COMMA.getText()) ? String.join(" .", this.classes.split(Delimiter.COMMA.getText())) : ".".concat(this.classes);
  }
}
