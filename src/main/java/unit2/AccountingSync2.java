package unit2;

/**
 * The Class AccountingSync2.
 */
public class AccountingSync2 implements Runnable {

  /** The instance. */
  static AccountingSync2 instance = new AccountingSync2();

  /** The i. */
  static int i = 0;

  /**
   * Increase.
   */
  private synchronized void increase() {
    i++;
  }

  /**
   * run
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    for (int j = 0; j < 100000; j++) {
      increase();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(instance);
    Thread t2 = new Thread(instance);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(i);
  }
}
