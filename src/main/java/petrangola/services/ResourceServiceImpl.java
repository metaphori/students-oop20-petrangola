package petrangola.services;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

public class ResourceServiceImpl implements ResourceService {
  private String resourceName;
  
  public ResourceServiceImpl() {}

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }
  
  public String getPath() {
    return Pattern.compile("\\\\/")
            .matcher(this.resourceName)
            .replaceAll(fileSeparator -> File.separator);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ResourceServiceImpl)) return false;
    ResourceServiceImpl that = (ResourceServiceImpl) o;
    return Objects.equals(resourceName, that.resourceName);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(resourceName);
  }
}
