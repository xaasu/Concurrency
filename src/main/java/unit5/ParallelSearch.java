package unit5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSearch {
  static int[] arr;
  static ExecutorService pool = Executors.newCachedThreadPool();
  static final int THREAD_NUM = 2;
  static AtomicInteger result = new AtomicInteger(-1);

  public static int search(int searchValue, int beginPos, int endPos) {
    if (result.get() >= 0) {
      return result.get();
    }
    for (int i = beginPos; i <= endPos; i++) {
      if (arr[i] == searchValue) {
        if (!result.compareAndSet(-1, i)) {
          return result.get();
        }
        return i;
      }
    }
    return -1;
  }

  public static class SearchTask implements Callable<Integer> {
    int begin, end, searchValue;

    public SearchTask(int begin, int end, int searchValue) {
      super();
      this.begin = begin;
      this.end = end;
      this.searchValue = searchValue;
    }


    @Override
    public Integer call() throws Exception {
      int re = search(searchValue, begin, end);
      return re;
    }
  }

  public static int pSearch(int searchValue) throws InterruptedException, ExecutionException {
    int subArrSize = arr.length / THREAD_NUM + 1;
    List<Future<Integer>> re = new ArrayList<>();
    for (int i = 0; i < arr.length; i += subArrSize) {
      int end = i + subArrSize;
      if (end > arr.length) {
        end = arr.length;
      }
      re.add(pool.submit(new SearchTask(i, end, searchValue)));
    }
    for (Future<Integer> fu : re) {
      if (fu.get() > 0) {
        return fu.get();
      }
    }
    return -1;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    arr = new int[] {1, 2, 3, 4, 5, 6, 7, 5, 6, 7, 8, 9, 4};
    pSearch(5);
  }
}
