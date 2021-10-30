package petrangola.views.components.table;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import petrangola.utlis.Pair;
import petrangola.views.components.AbstractComponentFX;

import java.util.List;
import java.util.Objects;

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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TableImpl)) return false;
    TableImpl<?> table = (TableImpl<?>) o;
    return getColumnHeadersPairs().equals(table.getColumnHeadersPairs());
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(getColumnHeadersPairs());
  }
}
