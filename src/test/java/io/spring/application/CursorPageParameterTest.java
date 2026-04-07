package io.spring.application;

import static org.junit.jupiter.api.Assertions.*;

import io.spring.application.CursorPager.Direction;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class CursorPageParameterTest {

  @Test
  public void should_create_with_valid_parameters() {
    DateTime cursor = new DateTime();
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(cursor, 10, Direction.NEXT);

    assertEquals(cursor, param.getCursor());
    assertEquals(10, param.getLimit());
    assertEquals(Direction.NEXT, param.getDirection());
  }

  @Test
  public void should_indicate_next_direction() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 10, Direction.NEXT);

    assertTrue(param.isNext());
  }

  @Test
  public void should_indicate_prev_direction() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 10, Direction.PREV);

    assertFalse(param.isNext());
  }

  @Test
  public void should_return_query_limit_as_limit_plus_one() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 10, Direction.NEXT);

    assertEquals(11, param.getQueryLimit());
  }

  @Test
  public void should_clamp_limit_exceeding_max_to_1000() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 2000, Direction.NEXT);

    assertEquals(1000, param.getLimit());
    assertEquals(1001, param.getQueryLimit());
  }

  @Test
  public void should_use_default_limit_for_negative_values() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, -5, Direction.NEXT);

    assertEquals(20, param.getLimit());
  }

  @Test
  public void should_use_default_limit_for_zero() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 0, Direction.NEXT);

    assertEquals(20, param.getLimit());
  }

  @Test
  public void should_accept_null_cursor() {
    CursorPageParameter<DateTime> param = new CursorPageParameter<>(null, 10, Direction.NEXT);

    assertNull(param.getCursor());
  }
}
