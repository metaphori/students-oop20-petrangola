package petrangola.models.game;

import java.util.*;
import java.util.stream.Collectors;
import petrangola.models.cards.*;
import petrangola.models.player.*;
import petrangola.utlis.DifficultyLevel;

public class GameFactoryImpl implements GameFactory {
  private final String username;
  private final int npcSize;
  private final DifficultyLevel difficultyLevel;
  
  public GameFactoryImpl(final String username, final int npcSize, final DifficultyLevel difficultyLevel) {
    this.username = username;
    this.npcSize = npcSize;
    this.difficultyLevel = difficultyLevel;
  }
  
  @Override
  public List<PlayerDetail> createPlayerDetails() {
    final List<Player> players = createPlayers();
    final List<PlayerDetail> playerDetails = new ArrayList<>();
    final List<Card> cards = new CardFactoryImpl()
                                   .createDeck()
                                   .stream()
                                   .limit(players.size())
                                   .collect(Collectors.toList());
    
    
    
    for (int index = 0; index < players.size() - 1; index++) {
      playerDetails.add(new PlayerDetailImpl(players.get(index), cards.get(index), index));
    }
    
    return playerDetails;
  }
  
  @Override
  public List<GameObject> createGameObject() {
    final List<PlayerDetail> list = createPlayerDetails();
    final Player dealerPlayer = list.stream()
                                    .collect(Collectors.toMap(PlayerDetail::getPlayer, playerDetail -> playerDetail.getHighCard().getValue()))
                                    .entrySet()
                                    .stream()
                                    .max(Map.Entry.comparingByValue())
                                    .get()
                                    .getKey();
    
    
    return list.stream().map(playerDetail -> {
      if (playerDetail.getPlayer().equals(dealerPlayer)) {
        return new DealerImpl(playerDetail.getPlayer());
      }
      
      return playerDetail.getPlayer();
    }).collect(Collectors.toList());
  }
  
  
  private List<Player> createPlayers() {
    final PlayerFactory playerFactory = new PlayerFactoryImpl();
    final List<Player> players = new ArrayList<>(playerFactory.createNPC(getNpcSize(), getDifficultyLevel()));
  
    players.add(playerFactory.createUser(getUsername()));
    
    return players;
  }
  
  private String getUsername() {
    return this.username;
  }
  
  private int getNpcSize() {
    return this.npcSize;
  }
  
  private DifficultyLevel getDifficultyLevel() {
    return this.difficultyLevel;
  }
}
