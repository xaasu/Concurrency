package unit3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ReenterLock.
 */
public class ReenterLock implements Runnable {

  /** The reentrant lock. */
  public static ReentrantLock reentrantLock = new ReentrantLock();

  /** The i. */
  public static int i = 0;


  /**
   * run
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    for (int j = 0; j < 100000; j++) {
      reentrantLock.lock();
      i++;
      reentrantLock.unlock();
    }
  }

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws InterruptedException the interrupted exception
   */
  public static void main(String[] args) throws InterruptedException {
    ReenterLock reenterLock = new ReenterLock();
    Thread t1 = new Thread(reenterLock);
    Thread t2 = new Thread(reenterLock);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(i);

  }
}
