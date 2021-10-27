package main.java.petrangola.views.game;

import main.java.petrangola.models.cards.Combination;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.components.table.TableFX;

import java.util.List;

public interface RankingView extends TableFX<RankedPlayer> {
  /**
   *
   * @param bestCombinations
   */
  void setBestCombinations(List<Pair<String, Combination>> bestCombinations);
  
  /**
   *
   */
  void loadRows();
  
  /**
   *
   * @param playersDetails
   */
  void setPlayersDetails(List<PlayerDetail> playersDetails);
}
