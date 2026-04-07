package io.spring.core.comment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CommentTest {

  @Test
  public void should_create_comment_with_all_fields() {
    Comment comment = new Comment("comment body", "user-123", "article-456");
    assertEquals("comment body", comment.getBody());
    assertEquals("user-123", comment.getUserId());
    assertEquals("article-456", comment.getArticleId());
    assertNotNull(comment.getId());
    assertNotNull(comment.getCreatedAt());
  }

  @Test
  public void should_generate_unique_ids() {
    Comment comment1 = new Comment("body1", "user-1", "article-1");
    Comment comment2 = new Comment("body2", "user-1", "article-1");
    assertNotEquals(comment1.getId(), comment2.getId());
  }

  @Test
  public void should_have_equal_comments_with_same_id() {
    Comment comment = new Comment("body", "user-1", "article-1");
    assertEquals(comment, comment);
  }

  @Test
  public void should_have_unequal_comments_with_different_ids() {
    Comment comment1 = new Comment("body", "user-1", "article-1");
    Comment comment2 = new Comment("body", "user-1", "article-1");
    assertNotEquals(comment1, comment2);
  }
}
