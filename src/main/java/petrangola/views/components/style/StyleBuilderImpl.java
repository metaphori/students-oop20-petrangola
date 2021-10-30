package petrangola.views.components.style;

import petrangola.utlis.UserAction;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;

public class StyleBuilderImpl implements StyleBuilder {
  private final Map<UserAction, String> stylesMap = new ConcurrentSkipListMap<>();
  
  @Override
  public StyleBuilder addStyle(String style, UserAction action) {
    stylesMap.putIfAbsent(action, style);
    
    return this;
  }
  
  @Override
  public String getStyles(UserAction action) {
    String styles = this.stylesMap.getOrDefault(action, "");
    
    if (!action.name().equals(UserAction.NOTHING.name())) {
      styles = this.stylesMap.get(UserAction.NOTHING).concat(styles);
    }
    
    return styles;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StyleBuilderImpl)) return false;
    StyleBuilderImpl that = (StyleBuilderImpl) o;
    return stylesMap.equals(that.stylesMap);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(stylesMap);
  }
}
