package unit2;

import java.util.ArrayList;

public class ArrayListMultiThread implements Runnable {

  static ArrayList<Integer> a1 = new ArrayList<>(10);

  @Override
  public void run() {
    for (int i = 0; i < 100000; i++) {
      a1.add(i);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new ArrayListMultiThread());
    Thread t2 = new Thread(new ArrayListMultiThread());
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(a1.size());
  }
}
