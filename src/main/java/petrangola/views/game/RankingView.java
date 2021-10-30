package petrangola.views.game;

import petrangola.models.cards.Combination;
import petrangola.models.player.PlayerDetail;
import petrangola.utlis.Pair;
import petrangola.views.components.table.TableFX;

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
