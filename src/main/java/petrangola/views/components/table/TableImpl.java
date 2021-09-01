package main.java.petrangola.views.components.table;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import main.java.petrangola.utlis.Pair;
import main.java.petrangola.views.components.AbstractComponentFX;

import java.util.List;

public abstract class TableImpl<T> extends AbstractComponentFX<TableView<T>> implements TableFX<T> {
  private final List<Pair<String, String>> columnHeadersPairs;
  
  public TableImpl(TableView<T> component, List<Pair<String, String>> columnHeadersPairs) {
    super(component);
    this.columnHeadersPairs = columnHeadersPairs;
  }
  
  protected List<Pair<String, String>> getColumnHeadersPairs()  {
    return this.columnHeadersPairs;
  }
  
  @Override
  public void addRows(List<T> list) {
    this.get().setItems((ObservableList<T>) list);
  }
}
