package main.java.petrangola.services;

import java.util.Objects;

public class ResourceServiceImpl implements ResourceService {
  private String resourceName;
  
  public ResourceServiceImpl() {}
  
  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }
  
  public String getPath() {
    return Objects.requireNonNull(getClass().getResource(this.resourceName)).toExternalForm();
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
