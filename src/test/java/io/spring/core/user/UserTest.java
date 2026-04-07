package io.spring.core.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

  @Test
  public void should_create_user_with_all_fields() {
    User user = new User("test@email.com", "testuser", "password", "bio", "image.png");

    assertNotNull(user.getId());
    assertEquals("test@email.com", user.getEmail());
    assertEquals("testuser", user.getUsername());
    assertEquals("password", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("image.png", user.getImage());
  }

  @Test
  public void should_generate_unique_ids() {
    User user1 = new User("a@email.com", "user1", "pass", "", "");
    User user2 = new User("b@email.com", "user2", "pass", "", "");

    assertNotEquals(user1.getId(), user2.getId());
  }

  @Test
  public void should_update_email_when_not_empty() {
    User user = new User("old@email.com", "user", "pass", "bio", "img");
    user.update("new@email.com", "", "", "", "");

    assertEquals("new@email.com", user.getEmail());
    assertEquals("user", user.getUsername());
  }

  @Test
  public void should_update_username_when_not_empty() {
    User user = new User("test@email.com", "oldname", "pass", "bio", "img");
    user.update("", "newname", "", "", "");

    assertEquals("newname", user.getUsername());
    assertEquals("test@email.com", user.getEmail());
  }

  @Test
  public void should_update_password_when_not_empty() {
    User user = new User("test@email.com", "user", "oldpass", "bio", "img");
    user.update("", "", "newpass", "", "");

    assertEquals("newpass", user.getPassword());
  }

  @Test
  public void should_update_bio_when_not_empty() {
    User user = new User("test@email.com", "user", "pass", "old bio", "img");
    user.update("", "", "", "new bio", "");

    assertEquals("new bio", user.getBio());
  }

  @Test
  public void should_update_image_when_not_empty() {
    User user = new User("test@email.com", "user", "pass", "bio", "old.png");
    user.update("", "", "", "", "new.png");

    assertEquals("new.png", user.getImage());
  }

  @Test
  public void should_not_update_fields_when_empty() {
    User user = new User("test@email.com", "user", "pass", "bio", "img");
    user.update("", "", "", "", "");

    assertEquals("test@email.com", user.getEmail());
    assertEquals("user", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("img", user.getImage());
  }

  @Test
  public void should_not_update_fields_when_null() {
    User user = new User("test@email.com", "user", "pass", "bio", "img");
    user.update(null, null, null, null, null);

    assertEquals("test@email.com", user.getEmail());
    assertEquals("user", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("img", user.getImage());
  }

  @Test
  public void should_update_all_fields_at_once() {
    User user = new User("old@email.com", "olduser", "oldpass", "old bio", "old.png");
    user.update("new@email.com", "newuser", "newpass", "new bio", "new.png");

    assertEquals("new@email.com", user.getEmail());
    assertEquals("newuser", user.getUsername());
    assertEquals("newpass", user.getPassword());
    assertEquals("new bio", user.getBio());
    assertEquals("new.png", user.getImage());
  }

  @Test
  public void should_have_equality_based_on_id() {
    User user1 = new User("a@email.com", "user1", "pass", "", "");
    User user2 = new User("b@email.com", "user2", "pass", "", "");

    assertNotEquals(user1, user2);
    assertEquals(user1, user1);
  }
}
