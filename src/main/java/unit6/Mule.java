package unit6;

public class Mule implements IHorse, IAnimal {
  public static void main(String[] args) {
    Mule m = new Mule();
    m.breath();
    m.run();
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    IAnimal.super.run();
  }
}
