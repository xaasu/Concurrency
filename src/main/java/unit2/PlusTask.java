package unit2;

public class PlusTask {
  private static volatile int i = 0;

  public static class Plus implements Runnable {

    @Override
    public void run() {
      for (int j = 0; j < 10000; j++) {
        i++;
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread[] threads = new Thread[10];
    for (int i = 0; i <= 9; i++) {
      threads[i] = new Thread(new Plus());
      threads[i].start();
    }
    for (int i = 0; i <= 9; i++) {
      threads[i].join();
    }
    System.out.println(i);
  }
}
