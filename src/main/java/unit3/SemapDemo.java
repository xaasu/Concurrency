package unit3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {

  final Semaphore semp = new Semaphore(5);

  @Override
  public void run() {
    try {
      semp.acquire();
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getId() + " done!");
      semp.release();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(20);
    final SemapDemo semap = new SemapDemo();
    for (int i = 0; i < 20; i++) {
      exec.submit(semap);
    }
  }
}
