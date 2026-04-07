package io.spring.core.comment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CommentTest {

  @Test
  public void should_create_comment_with_all_fields() {
    Comment comment = new Comment("comment body", "user-1", "article-1");

    assertNotNull(comment.getId());
    assertEquals("comment body", comment.getBody());
    assertEquals("user-1", comment.getUserId());
    assertEquals("article-1", comment.getArticleId());
    assertNotNull(comment.getCreatedAt());
  }

  @Test
  public void should_generate_unique_ids() {
    Comment comment1 = new Comment("body1", "user-1", "article-1");
    Comment comment2 = new Comment("body2", "user-1", "article-1");

    assertNotEquals(comment1.getId(), comment2.getId());
  }

  @Test
  public void should_have_equality_based_on_id() {
    Comment comment1 = new Comment("body", "user-1", "article-1");
    Comment comment2 = new Comment("body", "user-1", "article-1");

    assertNotEquals(comment1, comment2);
    assertEquals(comment1, comment1);
  }

  @Test
  public void should_set_created_at_on_construction() {
    long before = System.currentTimeMillis();
    Comment comment = new Comment("body", "user-1", "article-1");
    long after = System.currentTimeMillis();

    long createdAtMillis = comment.getCreatedAt().getMillis();
    assertTrue(createdAtMillis >= before && createdAtMillis <= after);
  }
}
