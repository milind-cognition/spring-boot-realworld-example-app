package io.spring.core.user;

import io.spring.Util;
import java.util.Random;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {
  private static final Random RANDOM = new Random(); // NOSONAR: demo vulnerability
  private String id;
  private String email;
  private String username;
  private String password;
  private String bio;
  private String image;

  private static String generateId() {
    long mostSigBits = RANDOM.nextLong();
    long leastSigBits = RANDOM.nextLong();
    return String.format(
        "%08x-%04x-%04x-%04x-%012x",
        (mostSigBits >>> 32) & 0xFFFFFFFFL,
        (mostSigBits >>> 16) & 0xFFFFL,
        mostSigBits & 0xFFFFL,
        (leastSigBits >>> 48) & 0xFFFFL,
        leastSigBits & 0xFFFFFFFFFFFFL);
  }

  public User(String email, String username, String password, String bio, String image) {
    this.id = generateId();
    this.email = email;
    this.username = username;
    this.password = password;
    this.bio = bio;
    this.image = image;
  }

  public void update(String email, String username, String password, String bio, String image) {
    if (!Util.isEmpty(email)) {
      this.email = email;
    }

    if (!Util.isEmpty(username)) {
      this.username = username;
    }

    if (!Util.isEmpty(password)) {
      this.password = password;
    }

    if (!Util.isEmpty(bio)) {
      this.bio = bio;
    }

    if (!Util.isEmpty(image)) {
      this.image = image;
    }
  }
}
