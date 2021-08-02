package petrangola.models.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import petrangola.models.cards.Cards;
import petrangola.models.player.Dealer;
import petrangola.models.player.Player;
import petrangola.utlis.DifficultyLevel;

public class GameImpl implements Game {
  private final List<GameObject> players;
  private int round;
  private int currentTurnNumber;
  private int knockerCount;
  private String lastKnocker;
  private Dealer dealer;
  private String winner;
  
  public GameImpl(final String username, final int npcSize, final DifficultyLevel difficultyLevel) {
    this.players = new ArrayList<>(new GameFactoryImpl(username, npcSize, difficultyLevel).createGameObject());
  }
  
  @Override
  public List<GameObject> getPlayers() {
    return this.players;
  }
  
  @Override
  public List<Cards> getCards() {
    final List<Player> players = getPlayers()
                                       .stream()
                                       .map(gameObject -> (Player) gameObject)
                                       .collect(Collectors.toList());
    
    return getDealer().dealCards(players);
  }
  
  @Override
  public int getRound() {
    return this.round;
  }
  
  @Override
  public void setRound(int round) {
    this.round = round;
  }
  
  @Override
  public int getCurrentTurnNumber() {
    return this.currentTurnNumber;
  }
  
  @Override
  public void setCurrentTurnNumber(int currentTurnNumber) {
    this.currentTurnNumber = currentTurnNumber;
  }
  
  @Override
  public int getKnockerCount() {
    return this.knockerCount;
  }
  
  @Override
  public void setKnockerCount(int knockerCount) {
    this.knockerCount = knockerCount;
  }
  
  @Override
  public String getLastKnocker() {
    return this.lastKnocker;
  }
  
  @Override
  public void setLastKnocker(String lastKnocker) {
    this.lastKnocker = lastKnocker;
  }
  
  @Override
  public Dealer getDealer() {
    return this.dealer;
  }
  
  @Override
  public void setDealer(Dealer dealer) {
    this.dealer = dealer;
  }
  
  @Override
  public String getWinner() {
    return this.winner;
  }
  
  @Override
  public void setWinner(String winner) {
    this.winner = winner;
  }
  
  @Override
  public boolean isOnlyOneRound() {
    final int size = getPlayers().size();
    
    if (size > 4) {
      return getKnockerCount() == 3;
    }
    
    return getKnockerCount() == getPlayers().size() - 1;
  }
}
