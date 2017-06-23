package unit6;

import java.util.Arrays;
import java.util.function.IntConsumer;

public class FunctionalCoding {
  public static void declarative() {
    int[] arr = {1, 2, 3, 5, 4, 5, 6};
    Arrays.stream(arr).forEach(System.out::print);
    System.out.println("\r\n------------------------------");
    Arrays.stream(arr).map((x) -> x = x + 1).forEach(System.out::print);
    System.out.println("\r\n------------------------------");
    Arrays.stream(arr).forEach(System.out::print);
  }

  static int[] arr = {1, 2, 3, 4, 8, 5, 6, 8, 4, 9};

  public static void ppz(int[] arr) {
    Arrays.stream(arr).forEach(new IntConsumer() {
      @Override
      public void accept(int value) {
        System.out.print(value);
      }
    });
    System.out.println("\r\n---------------");
    Arrays.stream(arr).forEach((final int x) -> {
      System.out.print(x);
    });
    System.out.println("\r\n---------------");
    Arrays.stream(arr).forEach((x) -> {
      System.out.print(x);
    });
    System.out.println("\r\n---------------");
    Arrays.stream(arr).forEach((x) -> System.out.print(x));
    System.out.println("\r\n---------------");
    IntConsumer outPrint = System.out::print;
    IntConsumer errPrint = System.out::print;
    Arrays.stream(arr).forEach(outPrint.andThen(errPrint));
  }


  public static void main(String[] args) {
    // declarative();
    ppz(arr);
  }
}
