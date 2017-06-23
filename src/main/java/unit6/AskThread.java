package unit6;

import java.util.concurrent.CompletableFuture;

public class AskThread implements Runnable {
  CompletableFuture<Integer> re = null;

  public AskThread(CompletableFuture<Integer> re) {
    super();
    this.re = re;
  }

  @Override
  public void run() {
    int myRe = 0;
    try {
      myRe = re.get() * re.get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(myRe);
  }

  public static void main(String[] args) throws InterruptedException {
    final CompletableFuture<Integer> future = new CompletableFuture<>();
    new Thread(new AskThread(future)).start();
    Thread.sleep(1000);
    future.complete(60);
  }
}
