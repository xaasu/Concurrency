package unit5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
    Producter p1 = new Producter(queue);
    Producter p2 = new Producter(queue);
    Producter p3 = new Producter(queue);
    Producter p4 = new Producter(queue);
    Consumer c1 = new Consumer(queue);
    Consumer c2 = new Consumer(queue);
    Consumer c3 = new Consumer(queue);
    Consumer c4 = new Consumer(queue);
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(p1);
    es.execute(p2);
    es.execute(p3);
    es.execute(p4);
    es.execute(c1);
    es.execute(c2);
    es.execute(c3);
    es.execute(c4);
    Thread.sleep(10 * 1000);
    p1.stop();
    p2.stop();
    p3.stop();
    Thread.sleep(3000);
    es.shutdown();
  }
}
