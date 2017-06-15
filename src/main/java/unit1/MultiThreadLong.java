package unit1;

/**
 * The Class MultiThreadLong.
 * 
 */
public class MultiThreadLong {

  /** The t. */
  public static long t = 0;

  /**
   * The Class ChangeT.
   */
  public static class ChangeT implements Runnable {

    /** The to. */
    private long to;

    /**
     * Instantiates a new change T.
     *
     * @param to the to
     */
    public ChangeT(long to) {
      this.to = to;
    }

    /**
     * run
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      while (true) {
        MultiThreadLong.t = to;
        Thread.yield();
      }
    }

  }

  /**
   * The Class ReadT.
   */
  public static class ReadT implements Runnable {

    /**
     * run
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      while (true) {
        long tmp = MultiThreadLong.t;
        if (tmp != 111L && tmp != 999L && tmp != 333L && tmp != 444L) {
          System.out.println(tmp);
        }
        Thread.yield();
      }
    }
  }

  /**
   * The main method. <br>
   * ��32λϵͳ�У���һ�����ӡ��4���е�����һ������Ϊlong��64λ��32λϵͳ��long�Ķ�д����ԭ���Եġ�
   * 
   * @param args the arguments
   */
  public static void main(String[] args) {
    new Thread(new ChangeT(111L)).start();
    new Thread(new ChangeT(999L)).start();
    new Thread(new ChangeT(333L)).start();
    new Thread(new ChangeT(444L)).start();
    new Thread(new ReadT()).start();
  }
}
