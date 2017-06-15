package unit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseDate implements Runnable {

  ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();

  int i = 0;

  public ParseDate(int i) {
    super();
    this.i = i;
  }


  @Override
  public void run() {
    if (t1.get() == null) {
      t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    try {
      Date t = t1.get().parse("2017-05-18 15:39:05");
      System.out.println(i + " : " + t);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      es.execute(new ParseDate(i));
    }
  }
}
