package petrangola.models.player;

import petrangola.models.cards.Card;
import petrangola.models.cards.Cards;
import petrangola.models.cards.CardsImpl;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserImpl implements User {
  private final String username;
  private final PropertyChangeSupport support = new PropertyChangeSupport(this);
  private boolean isDealer = false;
  
  public UserImpl(final String username) {
    this.username = username;
  }
  
  @Override
  public void firstExchange(Cards boardCards, Cards playerCards) {
    boardCards.getBoard().ifPresent(board -> {
      playerCards.getPlayer().ifPresent(player -> {
        Cards tempBoardCards = new CardsImpl(playerCards.getCombination(), board);
        Cards tempPlayerCards = new CardsImpl(boardCards.getCombination(), player);
        
        firePropertyChange("firstExchange", null, List.of(tempPlayerCards, tempBoardCards));
      });
    });
  }
  
  @Override
  public void exchange(Cards boardCards, Cards playerCards) {
    List<Card> playerChosenCards = new ArrayList<>(playerCards.getCombination().getChosenCards());
    List<Card> boardChosenCards = new ArrayList<>(boardCards.getCombination().getChosenCards());
    
    boardCards.getCombination().replaceCards(playerChosenCards, boardChosenCards);
    playerCards.getCombination().replaceCards(boardChosenCards, playerChosenCards);
    
    firePropertyChange("exchange", null, List.of(boardCards, playerCards));
  }
  
  @Override
  public String getUsername() {
    return this.username;
  }
  
  @Override
  public boolean isNPC() {
    return false;
  }
  
  @Override
  public boolean isDealer() {
    return this.isDealer;
  }
  
  @Override
  public void setIsDealer(boolean isDealer) {
    this.isDealer = isDealer;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserImpl)) return false;
    UserImpl user = (UserImpl) o;
    return isDealer() == user.isDealer() && getUsername().equals(user.getUsername()) && getSupport().equals(user.getSupport());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getUsername(), getSupport(), isDealer());
  }
  
  @Override
  public PropertyChangeSupport getSupport() {
    return this.support;
  }
}
