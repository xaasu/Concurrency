package unit3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExtThreadPool {
  public static class MyTask implements Runnable {

    protected String name;

    public MyTask(String name) {
      super();
      this.name = name;
    }

    @Override
    public void run() {
      System.out.println(System.currentTimeMillis() + ":Thread id " + Thread.currentThread().getId()
          + ", Task name : " + name);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<Runnable>()) {
      @Override
      protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("Before execute : " + ((MyTask) r).name);
      }

      @Override
      protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("After execute : " + ((MyTask) r).name);
      }

      @Override
      protected void terminated() {
        System.out.println("Terminate !");
      }
    };
    for (int i = 0; i < 5; i++) {
      MyTask task = new MyTask("Thread " + i);
      es.execute(task);
      Thread.sleep(10);
    }
    es.shutdown();
  }
}
