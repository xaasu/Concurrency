package unit6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureSimulation {
  public static Integer calc(Integer para) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return para / 2;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final CompletableFuture<Void> future =
        CompletableFuture.supplyAsync(() -> calc(50)).exceptionally(ex -> {
          System.out.println(ex.toString());
          return 0;
        }).thenApply((i) -> Integer.toString(i)).thenApply((str) -> "\"" + str + "\"")
            .thenAccept(System.out::println);
    future.get();
  }
}
