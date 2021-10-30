package petrangola.utlis;

import petrangola.services.ResourceService;
import petrangola.services.ResourceServiceImpl;

import java.io.File;

public enum Background {
  MENU("/petrangola/menu_image.png"), MENU_2("/petrangola/auth_image_dark.png"), GAME("/petrangola/game_image.png");
  
  private final ResourceService service = new ResourceServiceImpl();
  
  Background(String filename) {
    this.service.setResourceName(filename);
  }
  
  public String getPath() {
    return this.service.getPath();
  }
}
