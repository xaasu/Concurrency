package unit3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
  public static class MyTask implements Runnable {

    @Override
    public void run() {
      System.out
          .println(System.currentTimeMillis() + ":Thread id" + Thread.currentThread().getId());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    MyTask task = new MyTask();
    ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(10), new ThreadFactory() {
          @Override
          public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            System.out.println("Create " + t);
            return t;
          }
        });
    for (int i = 0; i < 5; i++) {
      es.submit(task);
    }
    Thread.sleep(2000);
  }
}
