package main.java.petrangola.views;

import javafx.scene.Group;
import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.game.KnockView;
import main.java.petrangola.views.game.RoundView;
import main.java.petrangola.views.game.WinnerView;
import main.java.petrangola.views.player.DealerView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.UserView;

public interface ViewNodeFactory {
  NPCView createNPCView(final PlayerDetail playerDetail, final CardsView<Group> cardsView);
  
  UserView createUserView(final PlayerDetail playerDetail, final CardsView<Group> cardsView);
  
  BoardView createBoardView(final CardsView<Group> cardsView);
  
  DealerView createDealerView(final PlayerDetail playerDetail, final CardsView<Group> cardsView);
  
  KnockView createKnockView();
  
  RoundView createRoundView();
  
  WinnerView createWinnerView();
}
