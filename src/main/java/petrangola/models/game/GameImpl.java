package main.java.petrangola.models.game;

import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Objects;
import main.java.petrangola.models.board.Board;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Dealer;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;

public class GameImpl implements Game {
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private List<PlayerDetail> playersDetails;
  private List<Player> players;
  private List<Cards> cards;
  private Board board;
  private Dealer dealer;
  private int round;
  private int currentTurnNumber;
  private int knockerCount;
  private String lastKnocker;
  private String winner;
  private boolean isOnlyOneRound;
  
  public GameImpl() {}
  
  @Override
  public List<Player> getPlayers() {
    return this.players;
  }
  
  @Override
  public void setPlayers(List<Player> players) {
    final List<Player> oldValue = this.players;
    this.players = players;
    firePropertyChange("players", oldValue, players);
  }
  
  @Override
  public Board getBoard() {
    return this.board;
  }
  
  @Override
  public void setBoard(Board board) {
    final Board oldValue = this.board;
    this.board = board;
    firePropertyChange("board", oldValue, board);
  }
  
  @Override
  public List<PlayerDetail> getPlayersDetails() {
    return this.playersDetails;
  }
  
  @Override
  public void setPlayersDetails(List<PlayerDetail> playersDetails) {
    final List<PlayerDetail> oldValue = this.playersDetails;
    this.playersDetails = playersDetails;
    firePropertyChange("playersDetails", oldValue, playersDetails);
  }
  
  @Override
  public List<Cards> getCards() {
    return this.cards;
  }
  
  @Override
  public void setCards(List<Cards> cards) {
    final List<Cards> oldValue = this.cards;
    this.cards = cards;
    firePropertyChange("cards", oldValue, cards);
  }
  
  @Override
  public int getRound() {
    return this.round;
  }
  
  @Override
  public void setRound(int round) {
    final int oldValue = this.round;
    this.round = round;
    firePropertyChange("round", oldValue, round);
  }
  
  @Override
  public int getCurrentTurnNumber() {
    return this.currentTurnNumber;
  }
  
  @Override
  public void setCurrentTurnNumber(int currentTurnNumber) {
    final int oldValue = this.currentTurnNumber;
    this.currentTurnNumber = currentTurnNumber;
    firePropertyChange("currentTurnNumber", oldValue, currentTurnNumber);
  }
  
  @Override
  public int getKnockerCount() {
    return this.knockerCount;
  }
  
  @Override
  public void setKnockerCount(int knockerCount) {
    final int oldValue = this.knockerCount;
    this.knockerCount = knockerCount;
    firePropertyChange("knockerCount", oldValue, knockerCount);
  }
  
  @Override
  public String getLastKnocker() {
    return this.lastKnocker;
  }
  
  @Override
  public void setLastKnocker(String lastKnocker) {
    final String oldValue = this.lastKnocker;
    this.lastKnocker = lastKnocker;
    firePropertyChange("lastKnocker", oldValue, lastKnocker);
  }
  
  @Override
  public Dealer getDealer() {
    return this.dealer;
  }
  
  @Override
  public void setDealer(Dealer dealer) {
    final Dealer oldValue = this.dealer;
    this.dealer = dealer;
    firePropertyChange("dealer", oldValue, dealer);
  }
  
  @Override
  public String getWinner() {
    return this.winner;
  }
  
  @Override
  public void setWinner(String winner) {
    final String oldValue = this.winner;
    this.winner = winner;
    firePropertyChange("winner", oldValue, winner);
  }
  
  @Override
  public void setOnlyOneRound(boolean isOnlyOneRound) {
    final boolean oldValue = this.isOnlyOneRound;
    this.isOnlyOneRound = isOnlyOneRound;
    firePropertyChange("onlyOneRound", oldValue, isOnlyOneRound);
  }
  
  @Override
  public boolean isOnlyOneRound() {
    return this.isOnlyOneRound;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GameImpl)) return false;
    GameImpl game = (GameImpl) o;
    return getRound() == game.getRound() && getCurrentTurnNumber() == game.getCurrentTurnNumber() && getKnockerCount() == game.getKnockerCount() && isOnlyOneRound() == game.isOnlyOneRound() && getSupport().equals(game.getSupport()) && getPlayersDetails().equals(game.getPlayersDetails()) && getPlayers().equals(game.getPlayers()) && getCards().equals(game.getCards()) && getBoard().equals(game.getBoard()) && getDealer().equals(game.getDealer()) && getLastKnocker().equals(game.getLastKnocker()) && getWinner().equals(game.getWinner());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getSupport(), getPlayersDetails(), getPlayers(), getCards(), getBoard(), getDealer(), getRound(), getCurrentTurnNumber(), getKnockerCount(), getLastKnocker(), getWinner(), isOnlyOneRound());
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
