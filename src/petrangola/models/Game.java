package petrangola.models;

import petrangola.models.player.Player;

import java.util.List;

public interface Game {
   /**
    *
    * @return
    */
   List<Player> getPlayers();
   
   /**
    *
    * @return
    */
   List<Cards> getCards();
   
   /**
    *
    * @return
    */
   int getRound();
   
   /**
    *
    * @return the number associated to the player that is currently playing
    */
   int getCurrentTurnNumber();
   
   /**
    *
    * @return counter value of the times players have knocked, when it reaches n - 1 ( when n players are < 5 ) or 3 the game is finished
    */
   int getKnockerCount();
   
   /**
    *
    * @return the last one that
    */
   String getLastKnocker();
   
   /**
    *
    * @return the username of the player that is giving cards to other players
    */
   String getDealer();
   
   /**
    *
    * @return the username of the winner
    */
   String getWinner();
   
   /**
    *
    * @return true if the dealer has taken the board cards
    */
   boolean isOnlyOneRound();
   
}
