package petrangola.views.player;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import petrangola.models.cards.Cards;
import petrangola.views.View;
import petrangola.views.cards.CardsView;
import petrangola.views.components.layout.LayoutBuilder;

import java.util.List;
import java.util.function.Predicate;

public interface GameObjectView extends View {
  /**
   * @return
   */
  static Predicate<? super GameObjectView> isDealerView() {
    return GameObjectView::isDealer;
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> isUserView() {
    return GameObjectView::isUser;
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> isNotBoard() {
    return gameObjectView -> !gameObjectView.getCardsView().getCards().isCommunity();
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> playerIsPresent() {
    return gameObjectView -> gameObjectView.getCardsView().getCards().getPlayer().isPresent();
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> playerIsNotNPC() {
    return gameObjectView -> !gameObjectView.getCardsView().getCards().getPlayer().get().isNPC();
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> playerIsNPC() {
    return playerIsNotNPC().negate();
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> playerIsNotDealer() {
    return gameObjectView -> !gameObjectView.getCardsView().getCards().getPlayer().get().isDealer();
  }
  
  /**
   * @return
   */
  static Predicate<? super GameObjectView> playerIsDealer() {
    return playerIsNotDealer().negate();
  }
  
  /**
   * @param layout
   * @param classes
   * @param toDisable
   */
  static void toggleVisibilityUserButton(Pane layout, List<String> classes, boolean toDisable) {
    final boolean isVisible = !toDisable;
    
    classes.forEach(className -> {
      final boolean isDisabled = className.equals(".Exchange") || toDisable;
      final Button button = (Button) layout.lookup(className);
      
      button.setDisable(isDisabled);
      button.setVisible(isVisible);
    });
  }
  
  /**
   *
   */
  void showCards();
  
  /**
   * @return
   */
  CardsView<Group> getCardsView();
  
  /**
   * @param cardsView
   */
  void setCardsView(CardsView<Group> cardsView);
  
  /**
   * @param cardsList
   */
  void updateCards(List<Cards> cardsList);
  
  /**
   * @param layout
   * @param layoutBuilder
   */
  void register(Pane layout, LayoutBuilder layoutBuilder);
  
  /**
   * @return
   */
  boolean isBoardView();
  
  /**
   * @return
   */
  boolean isDealer();
  
  /**
   * @return
   */
  boolean isUser();
}
