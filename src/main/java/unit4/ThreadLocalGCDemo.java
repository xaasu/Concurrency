package unit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalGCDemo {
  static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected void finalize() throws Throwable {
      System.out.println(this.toString() + " is gc ");
    }
  };
  static volatile CountDownLatch cd = new CountDownLatch(10000);

  public static class ParseDate implements Runnable {

    int i = 0;

    public ParseDate(int i) {
      super();
      this.i = i;
    }


    @Override
    public void run() {
      try {
        if (t1.get() == null) {
          t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {

            /** The Constant serialVersionUID. */
            private static final long serialVersionUID = -7081605060393999710L;

            @Override
            protected void finalize() throws Throwable {
              System.out.println(this.toString() + " is gc ");
            }
          });
          System.out.println(Thread.currentThread().getId() + ": create SimpleDateFormat .");
        }
        Date t = t1.get().parse("2017-05-18 15:39:05");
        System.out.println(i + " : " + t);
      } catch (ParseException e) {
        e.printStackTrace();
      } finally {
        cd.countDown();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10000; i++) {
      es.execute(new ParseDate(i));
    }
    cd.await();
    System.out.println("mission complete!");
    t1 = null;
    System.gc();
    System.out.println("first GC complete!");
    t1 = new ThreadLocal<>();
    cd = new CountDownLatch(10000);
    for (int i = 0; i < 10000; i++) {
      es.execute(new ParseDate(i));
    }
    cd.await();
    Thread.sleep(1000);
    t1 = null;
    System.gc();
    System.out.println("second GC complete!");
  }
}
