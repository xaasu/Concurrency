package unit6;

import java.util.ArrayList;
import java.util.List;

public class ConstrMethodRef {
  @FunctionalInterface
  interface UserFactory<U extends User> {

    U create(String id, String name);
  }

  static UserFactory<User> uf = User::new;

  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      users.add(uf.create(String.valueOf(i), "name " + i));
    }
    users.stream().map(User::getName).forEach(System.out::println);
  }
}
