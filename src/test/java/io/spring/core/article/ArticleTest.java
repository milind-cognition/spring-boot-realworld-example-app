package io.spring.core.article;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class ArticleTest {

  @Test
  public void should_get_right_slug() {
    Article article = new Article("a new   title", "desc", "body", Arrays.asList("java"), "123");
    assertThat(article.getSlug(), is("a-new-title"));
  }

  @Test
  public void should_get_right_slug_with_number_in_title() {
    Article article = new Article("a new title 2", "desc", "body", Arrays.asList("java"), "123");
    assertThat(article.getSlug(), is("a-new-title-2"));
  }

  @Test
  public void should_get_lower_case_slug() {
    Article article = new Article("A NEW TITLE", "desc", "body", Arrays.asList("java"), "123");
    assertThat(article.getSlug(), is("a-new-title"));
  }

  @Test
  public void should_handle_other_language() {
    Article article = new Article("中文：标题", "desc", "body", Arrays.asList("java"), "123");
    assertThat(article.getSlug(), is("中文-标题"));
  }

  @Test
  public void should_handle_commas() {
    Article article = new Article("what?the.hell,w", "desc", "body", Arrays.asList("java"), "123");
    assertThat(article.getSlug(), is("what-the-hell-w"));
  }

  @Test
  public void should_create_article_with_all_fields() {
    DateTime createdAt = new DateTime();
    Article article =
        new Article("title", "desc", "body", Arrays.asList("java", "spring"), "user-1", createdAt);
    assertEquals("title", article.getTitle());
    assertEquals("desc", article.getDescription());
    assertEquals("body", article.getBody());
    assertEquals("user-1", article.getUserId());
    assertEquals(createdAt, article.getCreatedAt());
    assertEquals(createdAt, article.getUpdatedAt());
    assertNotNull(article.getId());
    assertEquals("title", article.getSlug());
  }

  @Test
  public void should_generate_unique_ids() {
    Article article1 = new Article("title", "desc", "body", Arrays.asList("java"), "user-1");
    Article article2 = new Article("title", "desc", "body", Arrays.asList("java"), "user-1");
    assertNotEquals(article1.getId(), article2.getId());
  }

  @Test
  public void should_deduplicate_tags() {
    Article article =
        new Article("title", "desc", "body", Arrays.asList("java", "java", "spring"), "user-1");
    List<String> tagNames =
        article.getTags().stream().map(Tag::getName).collect(Collectors.toList());
    assertEquals(2, tagNames.size());
    assertTrue(tagNames.contains("java"));
    assertTrue(tagNames.contains("spring"));
  }

  @Test
  public void should_update_title_and_slug() {
    Article article = new Article("old title", "desc", "body", Arrays.asList("java"), "user-1");
    DateTime originalUpdatedAt = article.getUpdatedAt();
    article.update("new title", null, null);
    assertEquals("new title", article.getTitle());
    assertEquals("new-title", article.getSlug());
    assertEquals("desc", article.getDescription());
    assertEquals("body", article.getBody());
  }

  @Test
  public void should_update_description_only() {
    Article article = new Article("title", "old desc", "body", Arrays.asList("java"), "user-1");
    article.update(null, "new desc", null);
    assertEquals("title", article.getTitle());
    assertEquals("new desc", article.getDescription());
    assertEquals("body", article.getBody());
  }

  @Test
  public void should_update_body_only() {
    Article article = new Article("title", "desc", "old body", Arrays.asList("java"), "user-1");
    article.update(null, null, "new body");
    assertEquals("title", article.getTitle());
    assertEquals("desc", article.getDescription());
    assertEquals("new body", article.getBody());
  }

  @Test
  public void should_not_update_when_all_params_empty() {
    Article article = new Article("title", "desc", "body", Arrays.asList("java"), "user-1");
    article.update("", "", "");
    assertEquals("title", article.getTitle());
    assertEquals("desc", article.getDescription());
    assertEquals("body", article.getBody());
  }

  @Test
  public void should_not_update_when_all_params_null() {
    Article article = new Article("title", "desc", "body", Arrays.asList("java"), "user-1");
    article.update(null, null, null);
    assertEquals("title", article.getTitle());
    assertEquals("desc", article.getDescription());
    assertEquals("body", article.getBody());
  }

  @Test
  public void should_update_multiple_fields_at_once() {
    Article article =
        new Article("old title", "old desc", "old body", Arrays.asList("java"), "user-1");
    article.update("new title", "new desc", "new body");
    assertEquals("new title", article.getTitle());
    assertEquals("new desc", article.getDescription());
    assertEquals("new body", article.getBody());
    assertEquals("new-title", article.getSlug());
  }
}
