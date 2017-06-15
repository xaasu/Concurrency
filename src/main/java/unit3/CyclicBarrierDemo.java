package unit3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
  public static class Solider implements Runnable {
    private String solider;
    private CyclicBarrier cyclic;


    public Solider(String solider, CyclicBarrier cyclic) {
      super();
      this.solider = solider;
      this.cyclic = cyclic;
    }


    @Override
    public void run() {
      try {
        cyclic.await();
        doWork();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }

    }

    void doWork() {
      try {
        Thread.sleep(Math.abs(new Random().nextInt() % 10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("任务完成!");
    }
  }
  public static class BarrierRun implements Runnable {

    boolean flag;
    int N;


    public BarrierRun(boolean flag, int n) {
      super();
      this.flag = flag;
      N = n;
    }


    @Override
    public void run() {
      if (flag) {
        System.out.println(N + "个任务完成！");
      } else {
        System.out.println(N + "个任务全部开启！");
        flag = true;
      }
    }
  }

  public static void main(String[] args) {
    final int N = 10;
    Thread[] allSolider = new Thread[10];
    boolean flag = false;
    CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
    for (int i = 0; i < N; ++i) {
      System.out.println("开启第" + i + "个任务");
      allSolider[i] = new Thread(new Solider("任务" + i, cyclic));
      allSolider[i].start();
    }
  }
}
