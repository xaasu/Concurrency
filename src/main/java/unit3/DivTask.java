package unit3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {
  int a, b;

  public DivTask(int a, int b) {
    super();
    this.a = a;
    this.b = b;
  }

  @Override
  public void run() {
    double re = a / b;
    System.out.println(re);
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ThreadPoolExecutor pools =
        new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
    for (int i = 0; i < 5; i++) {
      Future re = pools.submit(new DivTask(100, i));
    }
  }
}
