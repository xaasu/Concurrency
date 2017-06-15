package unit2;

/**
 * The Class PriorityDemo.
 */
public class PriorityDemo {

  /**
   * The Class HighPriority.
   */
  public static class HighPriority extends Thread {

    /** The count. */
    static int count = 0;

    /**
     * run
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
      while (true) {
        synchronized (PriorityDemo.class) {
          count++;
          if (count > 100000) {
            System.out.println("HighPriority completed!");
            break;
          }
        }
      }
    }
  }

  /**
   * The Class LowPriority.
   */
  public static class LowPriority extends Thread {

    /** The count. */
    static int count = 0;

    /**
     * run
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
      while (true) {
        synchronized (PriorityDemo.class) {
          count++;
          if (count > 100000) {
            System.out.println("LowPriority completed!");
            break;
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
    Thread high = new HighPriority();
    Thread low = new LowPriority();
    high.setPriority(Thread.MAX_PRIORITY);
    low.setPriority(Thread.MIN_PRIORITY);
    low.start();
    high.start();
  }
}
