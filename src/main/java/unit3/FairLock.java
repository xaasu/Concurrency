package unit3;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {

  public static ReentrantLock lock = new ReentrantLock(true);

  @Override
  public void run() {
    while (true) {
      try {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "received lock!");

      } finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) {
    FairLock fl = new FairLock();
    Thread t1 = new Thread(fl, "t1");
    Thread t2 = new Thread(fl, "t2");
    t1.start();
    t2.start();
  }
}
