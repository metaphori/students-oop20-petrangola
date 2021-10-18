package main.java.petrangola.views.player.commands;

import main.java.petrangola.controllers.player.PlayerController;
import main.java.petrangola.models.cards.Cards;
import main.java.petrangola.models.player.Player;
import main.java.petrangola.views.Command;
import main.java.petrangola.views.cards.CardsExchanged;

import java.util.Optional;

public class ExchangeCommand implements Command {
  private final PlayerController playerController;
  private final Player player;
  private CardsExchanged cardsExchanged;
  
  public ExchangeCommand(final PlayerController playerController, final Player player) {
    this.playerController = playerController;
    this.player = player;
  }
  
  @Override
  public void execute() {
    this.cardsExchanged.getBoardCards().ifPresent(boardCards -> {
      this.cardsExchanged.getPlayerCards().ifPresent(playerCards -> {
        this.playerController.exchangeCards(this.player, boardCards, playerCards);
      });
    });
    
  }
  
  public void setCardsExchanged(CardsExchanged cardsExchanged) {
    this.cardsExchanged = cardsExchanged;
  }
  
  public boolean check() {
    final Optional<Cards> playerCards = this.cardsExchanged.getPlayerCards();
    final Optional<Cards> boardCards = this.cardsExchanged.getBoardCards();
    
    if (playerCards.isEmpty() || boardCards.isEmpty()) {
      return false;
    }
    
    final int sizePlayerCards = playerCards.get().getCombination().getChosenCards().size();
    final int sizeBoardCards = boardCards.get().getCombination().getChosenCards().size();
    
    return sizeBoardCards > 0 && sizeBoardCards == sizePlayerCards;
  }
}
