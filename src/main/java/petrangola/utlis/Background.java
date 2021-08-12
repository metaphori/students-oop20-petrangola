package main.java.petrangola.utlis;

import main.java.petrangola.services.ResourceService;
import main.java.petrangola.services.ResourceServiceImpl;
import java.io.File;

public enum Background {
  MENU("/menu_image.png"), MENU_2("/auth_image_dark.png"), GAME("/game_image.png");
  
  private final ResourceService service = new ResourceServiceImpl();
  
  Background(String filename) {
    this.service.setResourceName(filename);
    // this.service.setResourceName(File.separator.concat(filename)); TODO: check why this one doesn't work?
  }
  
  public String getPath() {
    return this.service.getPath();
  }
}
