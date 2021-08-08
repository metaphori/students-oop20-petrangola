package petrangola.models.player.npc;

import java.util.*;
import petrangola.models.cards.Cards;
import petrangola.utlis.Delimiter;
import petrangola.utlis.DifficultyLevel;

public class NPCImpl implements NPC {
  private static final String NPC_NAME = "NPC_";
  private final DifficultyLevel difficultyLevel;
  private final int id;
  
  private static final Map<DifficultyLevel, DrawbackStrategy> STRATEGY_MAP = new EnumMap<>(DifficultyLevel.class);
  static {
    STRATEGY_MAP.put(DifficultyLevel.EASY, new EasyDrawback());
    STRATEGY_MAP.put(DifficultyLevel.INTERMEDIATE, new IntermediateDrawback());
    STRATEGY_MAP.put(DifficultyLevel.ADVANCED, new AdvancedDrawback());
  }
  
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
    return NPC_NAME.concat(Delimiter.UNDERSCORE.toString()).concat(String.valueOf(this.id));
  }
  
  @Override
  public boolean isNPC() {
    return true;
  }
  
  @Override
  public List<Cards> exchange(final Cards boardCards, final Cards playerCards) {
    final double drawback = getDrawback();
    final Random random = new Random();
    ChoiceStrategy choiceStrategy;
  
    if (random.nextInt(100) < drawback) {
      choiceStrategy = new RandomChoice();
    } else {
      choiceStrategy = new BestChoice();
    }
    
    return choiceStrategy.chooseCards(List.of(boardCards, playerCards));
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
    return Objects.hash(getDifficultyLevel(), id);
  }
}
