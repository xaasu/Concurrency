package unit3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
  public static class MyTask implements Runnable {

    @Override
    public void run() {
      System.out
          .println(System.currentTimeMillis() + ":Thread id " + Thread.currentThread().getId());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    public static void main(String[] args) {
      MyTask task = new MyTask();
      ExecutorService exec = Executors.newFixedThreadPool(5);
      for (int i = 0; i < 10; i++) {
        exec.submit(task);
      }
    }
  }
}
