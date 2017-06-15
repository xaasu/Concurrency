package unit5;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producter implements Runnable {

  private boolean isRunning = true;
  private BlockingQueue<PCData> queue;
  private static AtomicInteger count = new AtomicInteger();
  private static final int SLEEPTIME = 1000;

  public Producter(BlockingQueue<PCData> queue) {
    super();
    this.queue = queue;
  }

  @Override
  public void run() {
    PCData data = null;
    Random r = new Random();
    System.out.println("start product id = " + Thread.currentThread().getId());
    try {
      while (isRunning) {
        Thread.sleep(r.nextInt(SLEEPTIME));
        data = new PCData(count.incrementAndGet());
        System.out.println(data + " is put into queue.");
        if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
          System.err.println("Failed to put data " + data);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  public void stop() {
    isRunning = false;
  }
}
