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
}
