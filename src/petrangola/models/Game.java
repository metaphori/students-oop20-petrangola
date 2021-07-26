package petrangola.models;

import java.util.List;

public interface Game {
   
   List<Player> getPlayers();
   
   List<Cards> getCards();
   
   int getRound();
   
   int getCurrentTurnNumber();
   
   int getKnockerCount();
   
   String getLastKnocker();
   
   String getDealer();
   
   String getWinner();
   
   boolean isOnlyOneRound();
   
}
