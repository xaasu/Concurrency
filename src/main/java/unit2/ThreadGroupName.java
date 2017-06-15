package unit2;

/**
 * The Class ThreadGroupName.
 */
public class ThreadGroupName implements Runnable {

  public static void main(String[] args) {
    ThreadGroup tg = new ThreadGroup("PrintGroup");
    Thread t1 = new Thread(tg, new ThreadGroupName(), "t1");
    Thread t2 = new Thread(tg, new ThreadGroupName(), "t2");
    t1.start();
    t2.start();
    System.out.println(tg.activeCount());
    tg.list();
  }

  /**
   * run
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    String groupAndName =
        Thread.currentThread().getThreadGroup().getName() + " " + Thread.currentThread().getName();
    while (true) {
      System.out.println(groupAndName);
      try {
        Thread.sleep(3000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
