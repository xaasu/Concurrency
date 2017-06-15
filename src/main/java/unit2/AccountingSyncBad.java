package unit2;

public class AccountingSyncBad implements Runnable {
  /** The instance. */
  static AccountingSync2 instance = new AccountingSync2();

  /** The i. */
  static int i = 0;

  /**
   * Increase.
   */
  private static synchronized void increase() {
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
    Thread t1 = new Thread(new AccountingSyncBad());
    Thread t2 = new Thread(new AccountingSyncBad());
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(i);
  }
}
