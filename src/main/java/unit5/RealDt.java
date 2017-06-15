package unit5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RealDt implements Callable<String> {

  private String para;

  public RealDt(String para) {
    super();
    this.para = para;
  }

  @Override
  public String call() throws Exception {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 10; i++) {
      sb.append(para);
      Thread.sleep(100);
    }
    return sb.toString();
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    FutureTask<String> task = new FutureTask<>(new RealDt("123"));
    ExecutorService executor = Executors.newFixedThreadPool(1);
    executor.submit(task);
    System.out.println("«Î«ÛÕÍ±œ");

    try {
      Thread.sleep(1000);
    } catch (Exception e) {
      // TODO: handle exception
    }
    System.out.println("Real data:" + task.get());
    executor.shutdownNow();
  }
}
