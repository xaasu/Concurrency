package unit5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEvenSort {
  static int[] arr;

  static int exchFlag = 1;

  static synchronized void setExchFlag(int v) {
    exchFlag = v;
  }

  static synchronized int getExchFlag() {
    return exchFlag;
  }

  public static class OddEvenSortTask implements Runnable {

    int i;

    CountDownLatch latch;

    public OddEvenSortTask(int i, CountDownLatch latch) {
      super();
      this.i = i;
      this.latch = latch;
    }

    @Override
    public void run() {
      if (arr[i] > arr[i + 1]) {
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
        setExchFlag(1);
      }
      latch.countDown();
    }
  }

  public static void pOddEvenSort(int[] arr) throws InterruptedException {
    int start = 0;
    ExecutorService pool = Executors.newCachedThreadPool();
    while (getExchFlag() == 1 || start == 1) {
      setExchFlag(0);
      CountDownLatch latch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
      for (int i = start; i < arr.length - 1; i += 2) {
        pool.submit(new OddEvenSortTask(i, latch));
      }
      latch.await();
      if (start == 0) {
        start = 1;
      } else {
        start = 0;
      }
    }
    pool.shutdownNow();
  }

  public static void main(String[] args) throws InterruptedException {
    arr =
        new int[] {8, 6, 4, 72, 0, 3, 5, 621, 1, 5, 61, 87, 9, 2, 2, 8, 1, 7, 9, 7, 1, 5, 263, 98};
    pOddEvenSort(arr);
  }
}
