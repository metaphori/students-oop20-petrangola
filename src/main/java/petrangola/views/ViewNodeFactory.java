package main.java.petrangola.views;

import main.java.petrangola.models.player.PlayerDetail;
import main.java.petrangola.views.board.BoardView;
import main.java.petrangola.views.cards.CardView;
import main.java.petrangola.views.cards.CardsView;
import main.java.petrangola.views.game.KnockView;
import main.java.petrangola.views.game.RoundView;
import main.java.petrangola.views.game.WinnerView;
import main.java.petrangola.views.player.DealerView;
import main.java.petrangola.views.player.NPCView;
import main.java.petrangola.views.player.UserView;

import java.util.List;

public interface ViewNodeFactory {
  NPCView createNPCView(final PlayerDetail playerDetail, final CardsView<List<CardView>> cardsView);
  
  UserView createUserView(final PlayerDetail playerDetail, final CardsView<List<CardView>> cardsView);
  
  BoardView createBoardView(final CardsView<List<CardView>> cardsView);
  
  DealerView createDealerView(final PlayerDetail playerDetail, final CardsView<List<CardView>> cardsView);
  
  KnockView createKnockView();
  
  RoundView createRoundView();
  
  WinnerView createWinnerView();
}
