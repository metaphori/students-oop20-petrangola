package main.java.petrangola.models.game;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import main.java.petrangola.models.player.*;

public class GameFactoryImpl implements GameFactory {
  
  public GameFactoryImpl() {}
  
  @Override
  public List<PlayerDetail> createPlayerDetails(final List<Player> players) {
    return IntStream.range(0, players.size())
                 .boxed()
                 .map(index -> new PlayerDetailImpl(players.get(index), index))
                 .collect(Collectors.toList());
  }
}
