package main.java.petrangola.controllers.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import main.java.petrangola.models.board.BoardImpl;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.CardFactoryImpl;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.game.GameFactory;
import main.java.petrangola.models.game.GameFactoryImpl;
import main.java.petrangola.models.player.*;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.utlis.Pair;

public class GameControllerImpl implements GameController {
  private final GameFactory gameFactory;
  private final PlayerFactory playerFactory;
  private final Game game;
  
  public GameControllerImpl(Game model) {
    this.game = model;
    this.gameFactory = new GameFactoryImpl();
    this.playerFactory = new PlayerFactoryImpl();
  }
  
  @Override
  public void createPlayers(final String username, final DifficultyLevel level, final int size) {
    final List<Player> players = new ArrayList<>();
    
    players.addAll(this.playerFactory.createNPC(size, level));
    players.add(this.playerFactory.createUser(username));
    
    this.game.setPlayers(players);
  }
  
  @Override
  public void createBoard() {
    this.game.setBoard(new BoardImpl());
  }
  
  @Override
  public void createPlayerDetails() {
    this.game.setPlayerDetails(this.gameFactory.createPlayerDetails(this.game.getPlayers()));
  }
  
  @Override
  public void createHighCards() {
    final int playersSize = this.game.getPlayers().size();
    final List<Card> cards = new CardFactoryImpl()
                                   .createDeck()
                                   .stream()
                                   .limit(playersSize)
                                   .collect(Collectors.toList());
    
  
    IntStream.range(0, playersSize)
          .boxed()
          .forEach(index -> this.game.getPlayerDetails().get(index).setHighCard(cards.get(index)));
  }
  
  @Override
  public void setDealer() {
    final Player dealerPlayer = this.game
                                      .getPlayerDetails()
                                      .stream()
                                      .collect(Collectors.toMap(PlayerDetail::getPlayer, playerDetail -> playerDetail.getHighCard().getValue()))
                                      .entrySet()
                                      .stream()
                                      .max(Map.Entry.comparingByValue())
                                      .get()
                                      .getKey();
    
    final Dealer dealer = new DealerImpl(dealerPlayer);
    
    this.game.setDealer(dealer);
  }
  
  @Override
  public void setWinner() {
    Player player = this.game
                          .getCards()
                          .stream()
                          .filter(Cards::isPlayerCards)
                          .map(cards -> new Pair<>(cards.getCombination().getBest().getY(), cards.getPlayer().get()))
                          .max(Comparator.comparingInt(Pair::getX))
                          .get()
                          .getY();
    
    this.game.setWinner(player.getUsername());
  }
  
  @Override
  public boolean checkKnocks() {
    final int size = this.game.getPlayers().size();
    final int knockerCount = this.game.getKnockerCount();
  
    if (size > 4) {
      return  knockerCount == 3;
    }
  
    return knockerCount == (size - 1);
  }
}
