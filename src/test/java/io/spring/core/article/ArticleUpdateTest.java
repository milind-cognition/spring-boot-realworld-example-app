package io.spring.core.article;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ArticleUpdateTest {

  @Test
  public void should_update_title_and_slug() {
    Article article = new Article("Old Title", "desc", "body", Arrays.asList("java"), "user-1");
    article.update("New Title", "", "");

    assertEquals("New Title", article.getTitle());
    assertEquals("new-title", article.getSlug());
  }

  @Test
  public void should_not_update_title_when_empty() {
    Article article = new Article("Original", "desc", "body", Arrays.asList("java"), "user-1");
    String originalSlug = article.getSlug();
    article.update("", "", "");

    assertEquals("Original", article.getTitle());
    assertEquals(originalSlug, article.getSlug());
  }

  @Test
  public void should_not_update_title_when_null() {
    Article article = new Article("Original", "desc", "body", Arrays.asList("java"), "user-1");
    article.update(null, null, null);

    assertEquals("Original", article.getTitle());
  }

  @Test
  public void should_update_description() {
    Article article = new Article("Title", "old desc", "body", Arrays.asList("java"), "user-1");
    article.update("", "new desc", "");

    assertEquals("new desc", article.getDescription());
  }

  @Test
  public void should_update_body() {
    Article article = new Article("Title", "desc", "old body", Arrays.asList("java"), "user-1");
    article.update("", "", "new body");

    assertEquals("new body", article.getBody());
  }

  @Test
  public void should_update_updated_at_when_title_changes() {
    Article article = new Article("Title", "desc", "body", Arrays.asList("java"), "user-1");
    var originalUpdatedAt = article.getUpdatedAt();
    article.update("New Title", "", "");

    assertTrue(
        article.getUpdatedAt().isEqual(originalUpdatedAt)
            || article.getUpdatedAt().isAfter(originalUpdatedAt));
  }

  @Test
  public void should_deduplicate_tags() {
    Article article =
        new Article("Title", "desc", "body", Arrays.asList("java", "java", "spring"), "user-1");

    assertEquals(2, article.getTags().size());
  }

  @Test
  public void should_create_article_with_generated_id() {
    Article article = new Article("Title", "desc", "body", Arrays.asList("java"), "user-1");

    assertNotNull(article.getId());
    assertFalse(article.getId().isEmpty());
  }

  @Test
  public void should_set_timestamps_on_creation() {
    Article article = new Article("Title", "desc", "body", Arrays.asList("java"), "user-1");

    assertNotNull(article.getCreatedAt());
    assertNotNull(article.getUpdatedAt());
    assertEquals(article.getCreatedAt(), article.getUpdatedAt());
  }

  @Test
  public void should_store_user_id() {
    Article article = new Article("Title", "desc", "body", Arrays.asList("java"), "user-123");

    assertEquals("user-123", article.getUserId());
  }

  @Test
  public void should_have_equality_based_on_id() {
    Article article1 = new Article("Title", "desc", "body", Arrays.asList("java"), "user-1");
    Article article2 = new Article("Title", "desc", "body", Arrays.asList("java"), "user-1");

    assertNotEquals(article1, article2);
    assertEquals(article1, article1);
  }
}
