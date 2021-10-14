package main.java.petrangola.views.game;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.Combination;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.components.table.TableImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RankingViewImpl extends TableImpl<RankedPlayer> implements RankingView {
  private static final Pair<String, String> USERNAME = new Pair<>("Username ↓", "username");
  private static final Pair<String, String> CARDS = new Pair<>("Cards ↓", "cards");
  private static final Pair<String, String> COMBINATION_VALUE = new Pair<>("Result ↓", "combinationValue");
  private static final Pair<String, String> IS_PETRANGOLA = new Pair<>("Petrangola? ↓", "isPetrangola");
  private static final Pair<String, String> LIVES = new Pair<>("Vite ↓", "playerLives");
  
  private List<Pair<String, Combination>> bestCombinations;
  private List<PlayerDetail> playersDetails;
  
  public RankingViewImpl(TableView<RankedPlayer> component) {
    super(component, List.of(USERNAME, CARDS, COMBINATION_VALUE, IS_PETRANGOLA));
    this.get().setEditable(false);
  }
  
  @Override
  public void setListeners() {
    // nothing
  }
  
  private List<Pair<String, Combination>> getBestCombinations() {
    return this.bestCombinations;
  }
  
  @Override
  public void setBestCombinations(List<Pair<String, Combination>> bestCombinations) {
    this.bestCombinations = bestCombinations;
  }
  
  @Override
  public void loadRows() {
    final ObservableList<RankedPlayer> data = FXCollections.observableArrayList();
    
    this.getBestCombinations()
          .stream()
          .map(pair -> {
            final String username = pair.getX();
            final Combination combination = pair.getY();
            final String cards = combination.getCards().stream().map(Card::getFullName).collect(Collectors.joining(" , "));
            final int combinationValue = combination.getBest().getY();
            final boolean isPetrangola = CombinationChecker.isFlush(combination.getBest().getX()) || CombinationChecker.isTris(combination.getBest().getX());
            
            return new RankedPlayerImpl(username, cards, combinationValue, isPetrangola);
          })
          .peek(rankedPlayer -> getPlayersDetails()
                                      .stream()
                                      .filter(playerDetail -> rankedPlayer.getUsername().equals(playerDetail.getPlayer().getUsername()))
                                      .findFirst()
                                      .ifPresent(playerDetail -> rankedPlayer.setPlayerLives(playerDetail.getPlayerLives()))
          ).forEachOrdered(data::add);
    
    
    super.getColumnHeadersPairs().forEach(pair -> {
      final TableColumn<RankedPlayer, String> column = new TableColumn<>(pair.getX());
      
      if (pair.getY().equals(IS_PETRANGOLA.getY())) {
        column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isPetrangola() ? " Yes " : " No "));
      } else {
        column.setCellValueFactory(new PropertyValueFactory<>(pair.getY()));
      }
      
      this.get().getColumns().add(column);
    });
    
    Comparator<RankedPlayer> comparator = new RankedPlayerComparator();
    
    FXCollections.sort(data, comparator);
    
    addRows(data);
  }
  
  @Override
  public void setPlayerDetails(List<PlayerDetail> playersDetails) {
    this.playersDetails = playersDetails;
  }
  
  public List<PlayerDetail> getPlayersDetails() {
    return this.playersDetails;
  }
  
  private static class RankedPlayerComparator implements Comparator<RankedPlayer> {
    @Override
    public int compare(RankedPlayer o1, RankedPlayer o2) {
      int value = Integer.compare(o1.getCombinationValue(), o2.getCombinationValue());
      
      if (o1.isPetrangola() && !o2.isPetrangola()) {
        value = 1;
      }
      
      if (o2.isPetrangola() && !o1.isPetrangola()) {
        value = -1;
      }
      
      return value * -1;
    }
  }
  
  public static class RankedPlayerImpl implements RankedPlayer {
    private final StringProperty username;
    private final StringProperty cards;
    private final IntegerProperty combinationValue;
    private final BooleanProperty isPetrangola;
    
    private IntegerProperty playerLives;
    
    public RankedPlayerImpl(final String username, final String cards, final int combinationValue, final boolean isPetrangola) {
      this.username = new SimpleStringProperty(username);
      this.cards = new SimpleStringProperty(cards);
      this.combinationValue = new SimpleIntegerProperty(combinationValue);
      this.isPetrangola = new SimpleBooleanProperty(isPetrangola);
    }
    
    @Override
    public String getUsername() {
      return username.get();
    }
    
    @Override
    public void setUsername(String username) {
      this.username.set(username);
    }
    
    @Override
    public String getCards() {
      return cards.get();
    }
    
    @Override
    public void setCards(String cards) {
      this.cards.set(cards);
    }
    
    @Override
    public int getCombinationValue() {
      return combinationValue.get();
    }
    
    @Override
    public void setCombinationValue(int combinationValue) {
      this.combinationValue.set(combinationValue);
    }
    
    @Override
    public boolean isPetrangola() {
      return isPetrangola.get();
    }
    
    @Override
    public void setIsPetrangola(boolean isPetrangola) {
      this.isPetrangola.set(isPetrangola);
    }
    
    @Override
    public int getPlayerLives() {
      return this.playerLives.get();
    }
    
    @Override
    public void setPlayerLives(int playerLives) {
      this.playerLives = new SimpleIntegerProperty(playerLives);
    }
  }
}
