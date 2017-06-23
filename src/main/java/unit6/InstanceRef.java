package unit6;

import java.util.ArrayList;
import java.util.List;


public class InstanceRef {
  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      users.add(new User(String.valueOf(i), "Number " + i));
    }
    users.stream().map(User::getName).forEach(System.out::println);
  }
}
