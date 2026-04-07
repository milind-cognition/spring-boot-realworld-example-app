package io.spring.core.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

  @Test
  public void should_create_user_with_all_fields() {
    User user = new User("email@test.com", "username", "password", "bio", "image");
    assertEquals("email@test.com", user.getEmail());
    assertEquals("username", user.getUsername());
    assertEquals("password", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("image", user.getImage());
    assertNotNull(user.getId());
  }

  @Test
  public void should_generate_unique_ids() {
    User user1 = new User("a@test.com", "user1", "pass", "", "");
    User user2 = new User("b@test.com", "user2", "pass", "", "");
    assertNotEquals(user1.getId(), user2.getId());
  }

  @Test
  public void should_update_email_when_not_empty() {
    User user = new User("old@test.com", "username", "pass", "bio", "image");
    user.update("new@test.com", null, null, null, null);
    assertEquals("new@test.com", user.getEmail());
    assertEquals("username", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("image", user.getImage());
  }

  @Test
  public void should_update_username_when_not_empty() {
    User user = new User("email@test.com", "old", "pass", "bio", "image");
    user.update(null, "newname", null, null, null);
    assertEquals("newname", user.getUsername());
    assertEquals("email@test.com", user.getEmail());
  }

  @Test
  public void should_update_password_when_not_empty() {
    User user = new User("email@test.com", "username", "oldpass", "bio", "image");
    user.update(null, null, "newpass", null, null);
    assertEquals("newpass", user.getPassword());
  }

  @Test
  public void should_update_bio_when_not_empty() {
    User user = new User("email@test.com", "username", "pass", "old bio", "image");
    user.update(null, null, null, "new bio", null);
    assertEquals("new bio", user.getBio());
  }

  @Test
  public void should_update_image_when_not_empty() {
    User user = new User("email@test.com", "username", "pass", "bio", "old.jpg");
    user.update(null, null, null, null, "new.jpg");
    assertEquals("new.jpg", user.getImage());
  }

  @Test
  public void should_not_update_fields_when_null() {
    User user = new User("email@test.com", "username", "pass", "bio", "image");
    user.update(null, null, null, null, null);
    assertEquals("email@test.com", user.getEmail());
    assertEquals("username", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("image", user.getImage());
  }

  @Test
  public void should_not_update_fields_when_empty_string() {
    User user = new User("email@test.com", "username", "pass", "bio", "image");
    user.update("", "", "", "", "");
    assertEquals("email@test.com", user.getEmail());
    assertEquals("username", user.getUsername());
    assertEquals("pass", user.getPassword());
    assertEquals("bio", user.getBio());
    assertEquals("image", user.getImage());
  }

  @Test
  public void should_update_multiple_fields_at_once() {
    User user = new User("old@test.com", "oldname", "oldpass", "old bio", "old.jpg");
    user.update("new@test.com", "newname", "newpass", "new bio", "new.jpg");
    assertEquals("new@test.com", user.getEmail());
    assertEquals("newname", user.getUsername());
    assertEquals("newpass", user.getPassword());
    assertEquals("new bio", user.getBio());
    assertEquals("new.jpg", user.getImage());
  }

  @Test
  public void should_have_equal_users_with_same_id() {
    User user1 = new User("a@test.com", "user1", "pass", "", "");
    assertEquals(user1, user1);
  }

  @Test
  public void should_have_unequal_users_with_different_ids() {
    User user1 = new User("a@test.com", "user1", "pass", "", "");
    User user2 = new User("a@test.com", "user1", "pass", "", "");
    assertNotEquals(user1, user2);
  }
}
