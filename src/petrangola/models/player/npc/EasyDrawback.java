package petrangola.models.player.npc;


public class EasyDrawback implements DrawbackStrategy {
  
  @Override
  public double getDrawback() {
    return 100.0;
  }
}
