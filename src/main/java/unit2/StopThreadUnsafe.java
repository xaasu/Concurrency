package unit2;

/**
 * The Class StopThreadUnsafe.
 */
public class StopThreadUnsafe {

  /** The user. */
  public static User user = new User();

  /**
   * The Class User.
   */
  public static class User {

    /** The id. */
    private int id;

    /** The name. */
    private String name;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
      return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
      this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
      return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * toString.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return "User [id=" + id + ", name=" + name + "]";
    }


    /**
     * Instantiates a new user.
     */
    public User() {
      this.id = 0;
      this.name = "0";
    }

  }

  /**
   * The Class ChangeObjectThread.
   */
  public static class ChangeObjectThread extends Thread {

    /** The stop. */
    volatile boolean stopMe = false;

    public void stopMe() {
      stopMe = true;
    }

    /**
     * run.
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
      while (true) {
        if (stopMe) {
          System.out.println("stop");
          break;
        }
        synchronized (user) {
          int v = (int) (System.currentTimeMillis() / 1000);
          user.setId(v);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          user.setName(String.valueOf(v));
        }
        Thread.yield();
      }
    }
  }

  /**
   * The Class ReadObjectThread.
   */
  public static class ReadObjectThread extends Thread {

    /**
     * run.
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
      while (true) {
        synchronized (user) {
          if (user.getId() != Integer.parseInt(user.getName())) {
            System.out.println(user.toString());
          }
        }
        Thread.yield();
      }
    }
  }

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws InterruptedException the interrupted exception
   */
  public static void main(String[] args) throws InterruptedException {
    new ReadObjectThread().start();
    while (true) {
      Thread t = new ChangeObjectThread();
      t.start();
      Thread.sleep(150);
      t.stop();
    }
  }
}
