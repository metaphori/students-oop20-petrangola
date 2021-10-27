package main.java.petrangola.views.events;

import main.java.petrangola.models.cards.*;
import main.java.petrangola.models.game.Game;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.Name;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.mediator.GameMediator;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WinnerEvent implements Event {
  private final Game game;
  private final GameMediator gameMediator;
  
  public WinnerEvent(final Game game, final GameMediator gameMediator) {
    this.game = game;
    this.gameMediator = gameMediator;
  }
  
  public List<Pair<String, Combination>> getBestCombinations() {
    final BestCombinationsComparator comparator = new BestCombinationComparatorImpl();
    return getCardsList()
                 .stream()
                 .filter(Cards::isPlayerCards)
                 .filter(cards -> cards.getPlayer().isPresent())
                 .map(cards -> new Pair<>(cards.getPlayer().get().getUsername(), cards.getCombination()))
                 .sorted((o1, o2) -> comparator.compare(o1.getY().getBest(), o2.getY().getBest()))
                 .collect(Collectors.toList());
  }
  
  public void takeLifeIfPossible() {
    final int size = (int) getCardsList().stream().filter(Cards::isPlayerCards).count();
    
    List<Pair<Player, Integer>> nonPetrangolaList = this.getPlayerValuePairs(List.of(pair -> !CombinationChecker.isAnyKindOfPetrangola(pair.getY().getCards())));
    
    int min = this.getMin(nonPetrangolaList);
    
    if (min == -1) {
      nonPetrangolaList = getPlayerValuePairs(List.of());
      min = this.getMin(nonPetrangolaList);
    }
    
    final int theActualMin = min;
    
    nonPetrangolaList.removeIf(pair -> pair.getY() > theActualMin);
    
    
    final boolean allMatch = nonPetrangolaList.stream().allMatch(pair -> pair.getY().equals(theActualMin));
    
    if (size > 2 || nonPetrangolaList.size() == 1 || !allMatch) {
      nonPetrangolaList.forEach(pair -> {
        this.getPlayersDetails().forEach(playerDetail -> {
          if (!playerDetail.getPlayer().equals(pair.getX())) {
            return;
          }
          
          playerDetail.lifeHandler(true);
        });
      });
    }
  }
  
  public void giveLifeIfPossible() {
    getCardsList()
          .stream()
          .filter(Cards::isPlayerCards)
          .filter(cards -> cards.getPlayer().isPresent())
          .map(cards -> new Pair<>(cards.getPlayer().get(), cards.getCombination()))
          .filter(pair -> {
            final List<Card> cards = pair.getY().getCards();
            return CombinationChecker.isTris(cards) && cards.get(0).getName().equals(Name.ASSO);
          })
          .findFirst()
          .ifPresent(pair -> {
            this.getPlayersDetails().forEach(playerDetail -> {
              if (!playerDetail.getPlayer().equals(pair.getX())) {
                return;
              }
              
              playerDetail.lifeHandler(false);
            });
          });
  }
  
  public List<Cards> getCardsList() {
    return getGame().getCards();
  }
  
  public List<PlayerDetail> getPlayersDetails() {
    return this.getGame()
                 .getPlayersDetails()
                 .stream()
                 .filter(PlayerDetail::isStillAlive)
                 .collect(Collectors.toList());
  }
  
  public Game getGame() {
    return this.game;
  }
  
  public GameMediator getGameMediator() {
    return this.gameMediator;
  }
  
  private List<Pair<Player, Integer>> getPlayerValuePairs(List<Predicate<? super Pair<Player, Combination>>> predicates) {
    return getCardsList()
                 .stream()
                 .filter(Cards::isPlayerCards)
                 .filter(cards -> cards.getPlayer().isPresent())
                 .map(cards -> new Pair<>(cards.getPlayer().get(), cards.getCombination()))
                 .filter(pair -> predicates.stream().anyMatch(predicate -> predicate.test(pair)))
                 .map(pair -> new Pair<>(pair.getX(), pair.getY().getBest().getY()))
                 .collect(Collectors.toList());
  }
  
  private Integer getMin(List<Pair<Player, Integer>> nonPetrangolaList) {
    return nonPetrangolaList
                 .stream()
                 .min(Comparator.comparingInt(Pair::getY))
                 .map(Pair::getY)
                 .orElse(-1);
  }
}
