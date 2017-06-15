package unit3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class TryLock.
 */
public class TryLock implements Runnable {

  /** The lock 1. */
  public static ReentrantLock lock1 = new ReentrantLock();

  /** The lock 2. */
  public static ReentrantLock lock2 = new ReentrantLock();

  /** The lock. */
  int lock;

  /**
   * Instantiates a new try lock.
   *
   * @param lock the lock
   */
  public TryLock(int lock) {
    super();
    this.lock = lock;
  }


  /**
   * run
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    if (lock == 1) {
      while (true) {
        if (lock1.tryLock()) {
          try {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            if (lock2.tryLock()) {
              try {
                System.out.println(Thread.currentThread().getId() + " job done");
                return;
              } finally {
                lock2.unlock();
              }
            }
          } finally {
            lock1.unlock();
          }
        }
      }
    } else {
      while (true) {
        if (lock2.tryLock()) {
          try {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            if (lock1.tryLock()) {
              try {
                System.out.println(Thread.currentThread().getId() + " job done");
                return;
              } finally {
                lock1.unlock();
              }
            }
          } finally {
            lock2.unlock();
          }
        }
      }
    }
  }

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    Thread t1 = new Thread(new TryLock(1));
    Thread t2 = new Thread(new TryLock(2));
    t1.start();
    t2.start();
  }
}

