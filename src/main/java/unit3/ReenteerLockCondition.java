package unit3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenteerLockCondition implements Runnable {

  public static ReentrantLock lock = new ReentrantLock();
  public static Condition condition = lock.newCondition();

  @Override
  public void run() {
    try {
      lock.lock();
      condition.await();
      System.out.println("Thread is going on");
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ReenteerLockCondition rl = new ReenteerLockCondition();
    Thread t1 = new Thread(rl);
    t1.start();
    Thread.sleep(2000);
    lock.lock();
    condition.signal();
    lock.unlock();
  }
}
