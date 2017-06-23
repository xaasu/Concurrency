package unit6;

import java.util.stream.IntStream;

public class PrimeUtil {
  public static boolean isPrime(int number) {
    int tmp = number;
    if (tmp < 2) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(tmp); i++) {
      if (tmp % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    long before = System.currentTimeMillis();
    // ´®ÐÐ
    IntStream.range(1, 1000000).filter(PrimeUtil::isPrime).count();
    System.out.println(System.currentTimeMillis() - before + "ms.");
    before = System.currentTimeMillis();
    // ²¢ÐÐ
    IntStream.range(1, 1000000).parallel().filter(PrimeUtil::isPrime).count();
    System.out.println(System.currentTimeMillis() - before + "ms.");

  }
}

