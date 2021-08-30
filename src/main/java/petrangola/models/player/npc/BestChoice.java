package main.java.petrangola.models.player.npc;

import main.java.petrangola.models.cards.Card;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.cards.Combination;
import main.java.petrangola.services.CombinationChecker;
import main.java.petrangola.utlis.DeckConstants;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.events.KnockEvent;
import org.greenrobot.eventbus.EventBus;
import org.paukov.combinatorics3.Generator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BestChoice extends AbstractChoiceStrategy {
  
  @Override
  public List<Cards> chooseCards(List<Cards> cardsList) {
    final List<Card> cardList = cardsList.stream()
                                      .map(Cards::getCombination)
                                      .map(Combination::getCards)
                                      .flatMap(List::stream)
                                      .collect(Collectors.toList());
    
    final Cards boardCards = getBoardCards(cardsList);
    final Cards playerCards = getPlayerCards(cardsList);
    
    
    final List<Card> maxCombination = getMaxCombinationListOfCards(cardList);
    final List<Card> complement = cardList
                                        .stream()
                                        .filter(card -> !maxCombination.contains(card))
                                        .collect(Collectors.toList());
    
    if (maxCombination.equals(playerCards.getCombination().getCards())) {
      EventBus.getDefault().post(new KnockEvent(playerCards.getPlayer().get()));
    }
    
    playerCards.getCombination().replaceCards(maxCombination, playerCards.getCombination().getCards());
    boardCards.getCombination().replaceCards(complement, boardCards.getCombination().getCards());
    
    return List.of(boardCards, playerCards);
  }
  
  private List<Card> getMaxCombinationListOfCards(List<Card> cardList) {
    List<List<Card>> combinations = generateAllCombinations(cardList);
    
    Optional<List<Card>> tris = combinations
                                      .stream()
                                      .filter(CombinationChecker::isTris)
                                      .findAny();
    
    if (tris.isPresent()) {
      return tris.get();
    }
    
    Optional<List<Card>> flush = combinations
                                       .stream()
                                       .filter(CombinationChecker::isFlush)
                                       .findAny();
    
    if (flush.isPresent()) {
      return flush.get();
    }
    
    Optional<List<Card>> flushWithAceLow = combinations
                                                 .stream()
                                                 .filter(CombinationChecker::isAceLow)
                                                 .findAny();
    
    return flushWithAceLow.orElseGet(() -> combinations
                                       .stream()
                                       .map(cards -> new Pair<>(cards, cards.stream()
                                                                             .collect(Collectors.groupingBy(Card::getSuit))
                                                                             .entrySet()
                                                                             .stream()
                                                                             .map(entry -> new Pair<>(entry.getKey(), entry.getValue().stream().mapToInt(Card::getValue).sum()))
                                                                             .max(Comparator.comparingInt(Pair::getY))
                                                                             .get()
                                                                             .getY()))
                                       .max(Comparator.comparingInt(Pair::getY))
                                       .get()
                                       .getX());
    
  }
  
  private List<List<Card>> generateAllCombinations(List<Card> cardList) {
    return Generator.combination(cardList)
                 .simple(DeckConstants.DECK_SIZE.getValue())
                 .stream()
                 .collect(Collectors.toList());
  }
}
