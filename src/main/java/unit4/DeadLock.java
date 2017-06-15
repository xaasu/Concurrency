package unit4;

public class DeadLock extends Thread {
  protected Object tool;
  static Object fork1 = new Object();
  static Object fork2 = new Object();

  public DeadLock(Object tool) {
    this.tool = tool;
    if (tool == fork1) {
      this.setName("��ѧ��A");
    }
    if (tool == fork2) {
      this.setName("��ѧ��B");
    }
  }

  @Override
  public void run() {
    if (tool == fork1) {
      synchronized (fork1) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (fork2) {
          System.out.println("��ѧ��A��ʼ�Է�");
        }
      }
    }
    if (tool == fork2) {
      synchronized (fork2) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (fork1) {
          System.out.println("��ѧ��A��ʼ�Է�");
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    DeadLock zA = new DeadLock(fork1);
    DeadLock zB = new DeadLock(fork2);
    zA.start();
    zB.start();
    Thread.sleep(1000);
  }
}
