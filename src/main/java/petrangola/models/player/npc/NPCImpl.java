package main.java.petrangola.models.player.npc;

import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.cards.CardsImpl;
import main.java.petrangola.models.cards.Combination;
import main.java.petrangola.utlis.Delimiter;
import main.java.petrangola.utlis.DifficultyLevel;

import java.beans.PropertyChangeSupport;
import java.util.*;

public class NPCImpl implements NPC {
  private static final String NPC_NAME = "NPC_";
  private static final Map<DifficultyLevel, DrawbackStrategy> STRATEGY_MAP = new EnumMap<>(DifficultyLevel.class);
  private boolean isDealer = false;
  
  static {
    STRATEGY_MAP.put(DifficultyLevel.EASY, new EasyDrawback());
    STRATEGY_MAP.put(DifficultyLevel.INTERMEDIATE, new IntermediateDrawback());
    STRATEGY_MAP.put(DifficultyLevel.ADVANCED, new AdvancedDrawback());
  }
  
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private final DifficultyLevel difficultyLevel;
  private final int id;
  
  public NPCImpl(final int id, final DifficultyLevel difficultyLevel) {
    this.id = id;
    this.difficultyLevel = difficultyLevel;
  }
  
  @Override
  public DifficultyLevel getDifficultyLevel() {
    return this.difficultyLevel;
  }
  
  @Override
  public String getUsername() {
    return NPC_NAME.concat(Delimiter.UNDERSCORE.getText()).concat(String.valueOf(this.id));
  }
  
  @Override
  public boolean isNPC() {
    return true;
  }
  
  @Override
  public boolean isDealer() {
    return isDealer;
  }
  
  @Override
  public void setIsDealer(boolean isDealer) {
    this.isDealer = isDealer;
  }
  
  @Override
  public void firstExchange(Cards boardCards, Cards playerCards) {
    final Random random = new Random();
    final Combination tempBoardCards = boardCards.getCombination();
    final Combination tempPlayerCards = playerCards.getCombination();
    
    if (random.nextBoolean() && playerCards.getPlayer().isPresent() && boardCards.getBoard().isPresent()) {
      playerCards = new CardsImpl(tempBoardCards, playerCards.getPlayer().get());
      boardCards = new CardsImpl(tempPlayerCards, boardCards.getBoard().get());
    }
    
    firePropertyChange("firstExchange", null, List.of(boardCards, playerCards));
  }
  
  @Override
  public void exchange(final Cards boardCards, final Cards playerCards) {
    final double drawback = getDrawback();
    final Random random = new Random();
    ChoiceStrategy choiceStrategy;
    
    if (random.nextInt(100) < drawback) {
      choiceStrategy = new RandomChoice();
    } else {
      choiceStrategy = new BestChoice();
    }
    
    firePropertyChange("exchange", null, choiceStrategy.chooseCards(List.of(boardCards, playerCards)));
  }
  
  private double getDrawback() {
    if (!STRATEGY_MAP.containsKey(getDifficultyLevel())) {
      throw new IllegalStateException("We don't that here");
    }
    
    return STRATEGY_MAP.get(getDifficultyLevel()).getDrawback();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NPCImpl)) return false;
    NPCImpl npc = (NPCImpl) o;
    return id == npc.id && getDifficultyLevel() == npc.getDifficultyLevel();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getSupport(), getDifficultyLevel(), id);
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
