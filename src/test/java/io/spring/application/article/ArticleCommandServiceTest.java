package io.spring.application.article;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import io.spring.core.article.Article;
import io.spring.core.article.ArticleRepository;
import io.spring.core.user.User;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArticleCommandServiceTest {

  private ArticleRepository articleRepository;
  private ArticleCommandService articleCommandService;

  @BeforeEach
  public void setUp() {
    articleRepository = mock(ArticleRepository.class);
    articleCommandService = new ArticleCommandService(articleRepository);
  }

  @Test
  public void should_create_article_and_save() {
    User creator = new User("author@email.com", "author", "pass", "", "");
    NewArticleParam param =
        NewArticleParam.builder()
            .title("Test Article")
            .description("A test description")
            .body("Article body content")
            .tagList(Arrays.asList("java", "spring"))
            .build();

    Article article = articleCommandService.createArticle(param, creator);

    assertNotNull(article);
    assertEquals("test-article", article.getSlug());
    assertEquals("Test Article", article.getTitle());
    assertEquals("A test description", article.getDescription());
    assertEquals("Article body content", article.getBody());
    assertEquals(creator.getId(), article.getUserId());
    assertEquals(2, article.getTags().size());
    verify(articleRepository).save(any(Article.class));
  }

  @Test
  public void should_update_article_title_and_save() {
    Article article = new Article("Old Title", "desc", "body", Arrays.asList("java"), "user-1");
    UpdateArticleParam param = new UpdateArticleParam("New Title", "", "");

    Article updated = articleCommandService.updateArticle(article, param);

    assertEquals("new-title", updated.getSlug());
    assertEquals("New Title", updated.getTitle());
    verify(articleRepository).save(article);
  }

  @Test
  public void should_update_article_description_and_save() {
    Article article =
        new Article("Title", "Old Description", "body", Arrays.asList("java"), "user-1");
    UpdateArticleParam param = new UpdateArticleParam("", "", "New Description");

    Article updated = articleCommandService.updateArticle(article, param);

    assertEquals("New Description", updated.getDescription());
    verify(articleRepository).save(article);
  }

  @Test
  public void should_update_article_body_and_save() {
    Article article = new Article("Title", "desc", "Old Body", Arrays.asList("java"), "user-1");
    UpdateArticleParam param = new UpdateArticleParam("", "New Body", "");

    Article updated = articleCommandService.updateArticle(article, param);

    assertEquals("New Body", updated.getBody());
    verify(articleRepository).save(article);
  }

  @Test
  public void should_update_all_fields_at_once() {
    Article article =
        new Article("Old Title", "Old Desc", "Old Body", Arrays.asList("java"), "user-1");
    UpdateArticleParam param = new UpdateArticleParam("New Title", "New Body", "New Desc");

    Article updated = articleCommandService.updateArticle(article, param);

    assertEquals("New Title", updated.getTitle());
    assertEquals("New Body", updated.getBody());
    assertEquals("New Desc", updated.getDescription());
    verify(articleRepository).save(article);
  }
}
