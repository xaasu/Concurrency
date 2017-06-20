package unit6;

import java.util.Arrays;

public class FunctionalCoding {
  public static void declarative() {
    int[] arr = {1, 2, 3, 5, 4, 5, 6};
    Arrays.stream(arr).forEach(System.out::print);
    System.out.println("\r\n------------------------------");
    Arrays.stream(arr).map((x) -> x = x + 1).forEach(System.out::print);
    System.out.println("\r\n------------------------------");
    Arrays.stream(arr).forEach(System.out::print);
  }

  public static void main(String[] args) {
    declarative();
  }
}
