package petrangola.models.game;

import java.util.List;
import java.util.Objects;

import petrangola.models.board.Board;
import petrangola.models.cards.Cards;
import petrangola.models.player.Dealer;
import petrangola.models.player.Player;
import petrangola.models.player.PlayerDetail;

public class GameImpl implements Game {
  private List<PlayerDetail> playerDetails;
  private List<Player> players;
  private List<Cards> cards;
  private Board board;
  private Dealer dealer;
  private int round;
  private int currentTurnNumber;
  private int knockerCount;
  private String lastKnocker;
  private String winner;
  private boolean onlyOneRound = false;
  
  public GameImpl() {}
  
  @Override
  public List<Player> getPlayers() {
    return this.players;
  }
  
  @Override
  public void setPlayers(List<Player> players) {
    this.players = players;
  }
  
  @Override
  public Board getBoard() {
    return this.board;
  }
  
  @Override
  public void setBoard(Board board) {
    this.board = board;
  }
  
  @Override
  public List<PlayerDetail> getPlayerDetails() {
    return this.playerDetails;
  }
  
  @Override
  public void setPlayerDetails(List<PlayerDetail> playerDetails) {
    this.playerDetails = playerDetails;
  }
  
  @Override
  public List<Cards> getCards() {
    /*final List<Player> players = getPlayers()
                                       .stream()
                                       .map(gameObject -> (Player) gameObject)
                                       .collect(Collectors.toList());
    
    return getDealer().dealCards(players);*/
    return this.cards;
  }
  
  @Override
  public void setCards(List<Cards> cards) {
    this.cards = cards;
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
  public void onlyOneRound() {
    this.onlyOneRound = true;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GameImpl)) return false;
    GameImpl game = (GameImpl) o;
    return getRound() == game.getRound() && getCurrentTurnNumber() == game.getCurrentTurnNumber() &&
                 getKnockerCount() == game.getKnockerCount() && getPlayers().equals(game.getPlayers()) &&
                 getLastKnocker().equals(game.getLastKnocker()) && getDealer().equals(game.getDealer()) &&
                 getWinner().equals(game.getWinner());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getPlayers(), getRound(), getCurrentTurnNumber(), getKnockerCount(), getLastKnocker(), getDealer(), getWinner());
  }
}
