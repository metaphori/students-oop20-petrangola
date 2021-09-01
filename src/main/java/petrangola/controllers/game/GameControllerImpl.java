package main.java.petrangola.controllers.game;

import main.java.petrangola.models.board.BoardImpl;
import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.CardFactoryImpl;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.*;
import main.java.petrangola.utlis.DifficultyLevel;
import main.java.petrangola.utlis.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameControllerImpl implements GameController {
  private final PlayerFactory playerFactory;
  private final Game game;
  
  public GameControllerImpl(Game model) {
    this.game = model;
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
    this.game.setPlayerDetails(this.game.getPlayers().stream().map(PlayerDetailImpl::new).collect(Collectors.toList()));
  }
  
  @Override
  public void createHighCards() {
    final int playersSize = this.game.getPlayers().size();
    final List<Card> cards = new CardFactoryImpl()
                                   .createDeck()
                                   .stream()
                                   .limit(playersSize)
                                   .collect(Collectors.toList());
    
    
    IntStream
          .range(0, playersSize)
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
    
    dealerPlayer.setIsDealer(true);
    
    final Dealer dealer = new DealerImpl(dealerPlayer);
    
    this.game.setDealer(dealer);
  }
  
  @Override
  public void setTurnNumbers() {
    List<PlayerDetail> playerDetails = new ArrayList<>(this.game.getPlayerDetails());
    int distance = 0;
    
    for (int index = 0; index <= playerDetails.size() - 1; index++) {
      if (playerDetails.get(index).getPlayer().isDealer()) {
        distance = index;
        break;
      }
    }
    
    Collections.rotate(playerDetails, -distance);
    
    IntStream.range(0, playerDetails.size())
          .boxed()
          .forEachOrdered(index -> {
            playerDetails.get(index).setTurnNumber(index);
          });
  }
  
  @Override
  public void setWinner(String winner) {
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
      return knockerCount == 3;
    }
    
    return knockerCount == (size - 1);
  }
  
  @Override
  public Player getCurrentPlayer() {
    return this.game
                 .getPlayerDetails()
                 .stream()
                 .filter(playerDetails -> playerDetails.getTurnNumber() == this.game.getCurrentTurnNumber())
                 .findFirst()
                 .get()
                 .getPlayer();
  }
  
  @Override
  public void nextTurnNumberHandler() {
    int nextTurnNumber = this.game.getCurrentTurnNumber() + 1;
    
    if (this.game.getPlayerDetails().size() - 1 == this.game.getCurrentTurnNumber()) {
      nextTurnNumber = 0;
    }
    
    this.game.setCurrentTurnNumber(nextTurnNumber);
  }
  
  @Override
  public void roundHandler() {
    int nextRound = this.game.getRound() + 1;
    this.game.setRound(nextRound);
  }
  
  @Override
  public void onlyOneRound() {
    this.game.onlyOneRound();
  }
  
  @Override
  public void addKnock(String username) {
    this.game.setKnockerCount(this.game.getKnockerCount() + 1);
    this.game.setLastKnocker(username);
  }
}
