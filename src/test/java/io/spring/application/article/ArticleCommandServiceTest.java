package io.spring.application.article;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import io.spring.core.article.Article;
import io.spring.core.article.ArticleRepository;
import io.spring.core.user.User;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArticleCommandServiceTest {

  @Mock private ArticleRepository articleRepository;

  private ArticleCommandService articleCommandService;

  @BeforeEach
  public void setUp() {
    articleCommandService = new ArticleCommandService(articleRepository);
  }

  @Test
  public void should_create_article_and_save() {
    User creator = new User("author@test.com", "author", "pass", "", "");
    NewArticleParam param =
        NewArticleParam.builder()
            .title("Test Article")
            .description("Test description")
            .body("Test body")
            .tagList(Arrays.asList("java", "spring"))
            .build();

    Article article = articleCommandService.createArticle(param, creator);

    assertNotNull(article);
    assertEquals("Test Article", article.getTitle());
    assertEquals("Test description", article.getDescription());
    assertEquals("Test body", article.getBody());
    assertEquals(creator.getId(), article.getUserId());
    assertEquals("test-article", article.getSlug());
    verify(articleRepository).save(any(Article.class));
  }

  @Test
  public void should_update_article_title() {
    User creator = new User("author@test.com", "author", "pass", "", "");
    Article article =
        new Article("Old Title", "Old desc", "Old body", Arrays.asList("java"), creator.getId());
    UpdateArticleParam updateParam = new UpdateArticleParam("New Title", "", "");

    Article updated = articleCommandService.updateArticle(article, updateParam);

    assertEquals("New Title", updated.getTitle());
    assertEquals("new-title", updated.getSlug());
    verify(articleRepository).save(article);
  }

  @Test
  public void should_update_article_body_and_description() {
    User creator = new User("author@test.com", "author", "pass", "", "");
    Article article =
        new Article("Title", "Old desc", "Old body", Arrays.asList("java"), creator.getId());
    UpdateArticleParam updateParam = new UpdateArticleParam("", "New body", "New desc");

    Article updated = articleCommandService.updateArticle(article, updateParam);

    assertEquals("Title", updated.getTitle());
    assertEquals("New body", updated.getBody());
    assertEquals("New desc", updated.getDescription());
    verify(articleRepository).save(article);
  }

  @Test
  public void should_not_change_article_when_update_params_are_empty() {
    User creator = new User("author@test.com", "author", "pass", "", "");
    Article article = new Article("Title", "Desc", "Body", Arrays.asList("java"), creator.getId());
    UpdateArticleParam updateParam = new UpdateArticleParam("", "", "");

    Article updated = articleCommandService.updateArticle(article, updateParam);

    assertEquals("Title", updated.getTitle());
    assertEquals("Desc", updated.getDescription());
    assertEquals("Body", updated.getBody());
    verify(articleRepository).save(article);
  }
}
