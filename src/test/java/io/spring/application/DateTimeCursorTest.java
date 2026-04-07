package io.spring.application;

import static org.junit.jupiter.api.Assertions.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;

public class DateTimeCursorTest {

  @Test
  public void should_create_cursor_from_datetime() {
    DateTime now = new DateTime();
    DateTimeCursor cursor = new DateTimeCursor(now);

    assertEquals(now, cursor.getData());
  }

  @Test
  public void should_convert_to_millis_string() {
    DateTime dateTime = new DateTime(1234567890000L);
    DateTimeCursor cursor = new DateTimeCursor(dateTime);

    assertEquals("1234567890000", cursor.toString());
  }

  @Test
  public void should_parse_millis_string_to_datetime() {
    DateTime result = DateTimeCursor.parse("1234567890000");

    assertNotNull(result);
    assertEquals(1234567890000L, result.getMillis());
    assertEquals(DateTimeZone.UTC, result.getZone());
  }

  @Test
  public void should_return_null_for_null_cursor_string() {
    DateTime result = DateTimeCursor.parse(null);

    assertNull(result);
  }

  @Test
  public void should_roundtrip_through_toString_and_parse() {
    DateTime original = new DateTime().withZone(DateTimeZone.UTC);
    DateTimeCursor cursor = new DateTimeCursor(original);

    DateTime parsed = DateTimeCursor.parse(cursor.toString());

    assertNotNull(parsed);
    assertEquals(original.getMillis(), parsed.getMillis());
  }
}
