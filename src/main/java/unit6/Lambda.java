package unit6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Lambda {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(12, 3, 4, 5, 6, 7, 8);
    numbers.forEach((Integer val) -> System.out.println(val));
    final int num = 2;
    Function<Integer, Integer> strConventer = (from) -> from * num;
    System.out.println(strConventer.apply(5));
  }
}
