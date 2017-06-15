package unit4;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
  static AtomicReference<Integer> money = new AtomicReference<>();

  public static void main(String[] args) {
    money.set(19);
    for (int i = 0; i < 3; i++) {
      new Thread() {
        public void run() {
          while (true) {
            while (true) {
              Integer m = money.get();
              if (m < 20) {
                if (money.compareAndSet(m, m + 20)) {
                  System.out.println("���С��20����ֵ�ɹ������:" + money.get() + "Ԫ");
                  break;
                }
              } else {
                //System.out.println("������20�������ֵ");
                break;
              }
            }
          }
        };
      }.start();
    }
    new Thread() {
      public void run() {
        for (int i = 0; i < 100; i++) {
          while (true) {
            Integer m = money.get();
            if (m > 10) {
              System.out.println("����10Ԫ");
              if (money.compareAndSet(m, m - 10)) {
                System.out.println("�ɹ�����10Ԫ,���:" + money.get());
                break;
              }
            } else {
              System.out.println("û���㹻���");
              break;
            }
          }
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
        }
      };
    }.start();
  }
}
