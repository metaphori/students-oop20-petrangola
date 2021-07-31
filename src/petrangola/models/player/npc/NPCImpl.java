package petrangola.models.player.npc;

import java.util.List;
import java.util.Random;
import petrangola.models.Cards;
import petrangola.utlis.Delimiter;
import petrangola.utlis.DifficultyLevel;

public class NPCImpl implements NPC {
  private static final String NPC_NAME = "NPC_NAME";
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
    return NPC_NAME
                 .concat(Delimiter.UNDERSCORE.toString())
                 .concat(String.valueOf(this.id));
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
    DrawbackStrategy strategy;
    
    switch (getDifficultyLevel()) {
      case EASY:
        strategy = new EasyDrawback();
        break;
      case INTERMEDIATE:
        strategy = new IntermediateDrawback();
        break;
      case ADVANCED:
        strategy = new AdvancedDrawback();
        break;
      default:
        throw new IllegalStateException();
    }
    
    return strategy.getDrawback();
  }
  
}
