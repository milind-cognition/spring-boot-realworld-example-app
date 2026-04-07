package io.spring.core.service;

import static org.junit.jupiter.api.Assertions.*;

import io.spring.core.article.Article;
import io.spring.core.comment.Comment;
import io.spring.core.user.User;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorizationServiceTest {

  private User articleAuthor;
  private User otherUser;
  private Article article;

  @BeforeEach
  public void setUp() {
    articleAuthor = new User("author@email.com", "author", "pass", "", "");
    otherUser = new User("other@email.com", "other", "pass", "", "");
    article = new Article("title", "desc", "body", Arrays.asList("java"), articleAuthor.getId());
  }

  @Test
  public void should_allow_article_author_to_write_article() {
    assertTrue(AuthorizationService.canWriteArticle(articleAuthor, article));
  }

  @Test
  public void should_not_allow_other_user_to_write_article() {
    assertFalse(AuthorizationService.canWriteArticle(otherUser, article));
  }

  @Test
  public void should_allow_article_author_to_delete_any_comment() {
    Comment comment = new Comment("body", otherUser.getId(), article.getId());

    assertTrue(AuthorizationService.canWriteComment(articleAuthor, article, comment));
  }

  @Test
  public void should_allow_comment_author_to_delete_own_comment() {
    Comment comment = new Comment("body", otherUser.getId(), article.getId());

    assertTrue(AuthorizationService.canWriteComment(otherUser, article, comment));
  }

  @Test
  public void should_not_allow_unrelated_user_to_delete_comment() {
    User unrelatedUser = new User("third@email.com", "third", "pass", "", "");
    Comment comment = new Comment("body", otherUser.getId(), article.getId());

    assertFalse(AuthorizationService.canWriteComment(unrelatedUser, article, comment));
  }
}
