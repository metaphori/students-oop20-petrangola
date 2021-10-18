package main.java.petrangola.views.events;

import javafx.scene.layout.Pane;
import main.java.petrangola.models.cards.*;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.Name;
import main.java.petrangola.utlis.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WinnerEvent implements Event {
  private final List<Cards> cardsList;
  private final List<PlayerDetail> playerDetails;
  private final Pane layout;
  
  public WinnerEvent(final List<Cards> cardsList, List<PlayerDetail> playerDetails, Pane layout) {
    this.cardsList = cardsList;
    this.playerDetails = playerDetails;
    this.layout = layout;
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
  
  public List<Cards> getCardsList() {
    return this.cardsList;
  }
  
  public List<PlayerDetail> getPlayerDetails() {
    return this.playerDetails;
  }
  
  public Pane getLayout() {
    return this.layout;
  }
  
  public void takeLifeIfPossible() {
    List<Pair<Player, Integer>> nonPetrangolaList = getCardsList()
                                                          .stream()
                                                          .filter(Cards::isPlayerCards)
                                                          .filter(cards -> cards.getPlayer().isPresent())
                                                          .map(cards -> new Pair<>(cards.getPlayer().get(), cards.getCombination()))
                                                          .filter(pair -> !CombinationChecker.isAnyKindOfPetrangola(pair.getY().getCards()))
                                                          .map(pair -> new Pair<>(pair.getX(), pair.getY().getBest().getY()))
                                                          .collect(Collectors.toList());
    
    int min = nonPetrangolaList
                    .stream()
                    .min(Comparator.comparingInt(Pair::getY))
                    .map(Pair::getY)
                    .orElse(-1);
    
    
    nonPetrangolaList
          .stream()
          .filter(pair -> pair.getY() <= min)
          .collect(Collectors.toList())
          .forEach(pair -> getPlayerDetails().forEach(playerDetail -> {
            if (!playerDetail.getPlayer().equals(pair.getX())) {
              return;
            }
            
            playerDetail.lifeHandler(true);
          }));
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
            getPlayerDetails().forEach(playerDetail -> {
              if (!playerDetail.getPlayer().equals(pair.getX())) {
                return;
              }
              
              playerDetail.lifeHandler(false);
            });
          });
  }
}
