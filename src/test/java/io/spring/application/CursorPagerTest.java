package io.spring.application;

import static org.junit.jupiter.api.Assertions.*;

import io.spring.application.CursorPager.Direction;
import io.spring.application.data.ArticleData;
import io.spring.application.data.ProfileData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class CursorPagerTest {

  private ArticleData createArticleData(String id) {
    DateTime now = new DateTime();
    return new ArticleData(
        id,
        "slug-" + id,
        "title",
        "desc",
        "body",
        false,
        0,
        now,
        now,
        new ArrayList<>(),
        new ProfileData("uid", "user", "bio", "img", false));
  }

  @Test
  public void should_indicate_has_next_when_direction_is_next_and_has_extra() {
    List<ArticleData> data = Arrays.asList(createArticleData("1"));
    CursorPager<ArticleData> pager = new CursorPager<>(data, Direction.NEXT, true);

    assertTrue(pager.hasNext());
    assertFalse(pager.hasPrevious());
  }

  @Test
  public void should_indicate_no_next_when_direction_is_next_and_no_extra() {
    List<ArticleData> data = Arrays.asList(createArticleData("1"));
    CursorPager<ArticleData> pager = new CursorPager<>(data, Direction.NEXT, false);

    assertFalse(pager.hasNext());
    assertFalse(pager.hasPrevious());
  }

  @Test
  public void should_indicate_has_previous_when_direction_is_prev_and_has_extra() {
    List<ArticleData> data = Arrays.asList(createArticleData("1"));
    CursorPager<ArticleData> pager = new CursorPager<>(data, Direction.PREV, true);

    assertFalse(pager.hasNext());
    assertTrue(pager.hasPrevious());
  }

  @Test
  public void should_indicate_no_previous_when_direction_is_prev_and_no_extra() {
    List<ArticleData> data = Arrays.asList(createArticleData("1"));
    CursorPager<ArticleData> pager = new CursorPager<>(data, Direction.PREV, false);

    assertFalse(pager.hasNext());
    assertFalse(pager.hasPrevious());
  }

  @Test
  public void should_return_start_cursor_from_first_element() {
    ArticleData first = createArticleData("1");
    ArticleData second = createArticleData("2");
    CursorPager<ArticleData> pager =
        new CursorPager<>(Arrays.asList(first, second), Direction.NEXT, false);

    assertNotNull(pager.getStartCursor());
    assertEquals(first.getCursor().getData(), pager.getStartCursor().getData());
  }

  @Test
  public void should_return_end_cursor_from_last_element() {
    ArticleData first = createArticleData("1");
    ArticleData second = createArticleData("2");
    CursorPager<ArticleData> pager =
        new CursorPager<>(Arrays.asList(first, second), Direction.NEXT, false);

    assertNotNull(pager.getEndCursor());
    assertEquals(second.getCursor().getData(), pager.getEndCursor().getData());
  }

  @Test
  public void should_return_null_cursors_for_empty_data() {
    CursorPager<ArticleData> pager = new CursorPager<>(new ArrayList<>(), Direction.NEXT, false);

    assertNull(pager.getStartCursor());
    assertNull(pager.getEndCursor());
  }

  @Test
  public void should_return_data_list() {
    List<ArticleData> data = Arrays.asList(createArticleData("1"), createArticleData("2"));
    CursorPager<ArticleData> pager = new CursorPager<>(data, Direction.NEXT, false);

    assertEquals(2, pager.getData().size());
  }
}
