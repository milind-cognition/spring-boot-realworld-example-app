package io.spring.core.service;

import static org.junit.jupiter.api.Assertions.*;

import io.spring.core.article.Article;
import io.spring.core.comment.Comment;
import io.spring.core.user.User;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AuthorizationServiceTest {

  @Test
  public void should_allow_article_author_to_write_article() {
    User author = new User("author@test.com", "author", "pass", "", "");
    Article article = new Article("title", "desc", "body", Arrays.asList("tag"), author.getId());
    assertTrue(AuthorizationService.canWriteArticle(author, article));
  }

  @Test
  public void should_deny_non_author_to_write_article() {
    User author = new User("author@test.com", "author", "pass", "", "");
    User other = new User("other@test.com", "other", "pass", "", "");
    Article article = new Article("title", "desc", "body", Arrays.asList("tag"), author.getId());
    assertFalse(AuthorizationService.canWriteArticle(other, article));
  }

  @Test
  public void should_allow_comment_author_to_delete_comment() {
    User articleAuthor = new User("author@test.com", "author", "pass", "", "");
    User commentAuthor = new User("commenter@test.com", "commenter", "pass", "", "");
    Article article =
        new Article("title", "desc", "body", Arrays.asList("tag"), articleAuthor.getId());
    Comment comment = new Comment("comment", commentAuthor.getId(), article.getId());
    assertTrue(AuthorizationService.canWriteComment(commentAuthor, article, comment));
  }

  @Test
  public void should_allow_article_author_to_delete_comment() {
    User articleAuthor = new User("author@test.com", "author", "pass", "", "");
    User commentAuthor = new User("commenter@test.com", "commenter", "pass", "", "");
    Article article =
        new Article("title", "desc", "body", Arrays.asList("tag"), articleAuthor.getId());
    Comment comment = new Comment("comment", commentAuthor.getId(), article.getId());
    assertTrue(AuthorizationService.canWriteComment(articleAuthor, article, comment));
  }

  @Test
  public void should_deny_unrelated_user_to_delete_comment() {
    User articleAuthor = new User("author@test.com", "author", "pass", "", "");
    User commentAuthor = new User("commenter@test.com", "commenter", "pass", "", "");
    User unrelated = new User("random@test.com", "random", "pass", "", "");
    Article article =
        new Article("title", "desc", "body", Arrays.asList("tag"), articleAuthor.getId());
    Comment comment = new Comment("comment", commentAuthor.getId(), article.getId());
    assertFalse(AuthorizationService.canWriteComment(unrelated, article, comment));
  }
}
