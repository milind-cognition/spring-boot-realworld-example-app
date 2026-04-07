package io.spring.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PageTest {

  @Test
  public void should_create_page_with_defaults() {
    Page page = new Page();

    assertEquals(0, page.getOffset());
    assertEquals(20, page.getLimit());
  }

  @Test
  public void should_create_page_with_valid_offset_and_limit() {
    Page page = new Page(10, 50);

    assertEquals(10, page.getOffset());
    assertEquals(50, page.getLimit());
  }

  @Test
  public void should_clamp_negative_offset_to_zero() {
    Page page = new Page(-5, 20);

    assertEquals(0, page.getOffset());
  }

  @Test
  public void should_clamp_zero_offset_to_zero() {
    Page page = new Page(0, 20);

    assertEquals(0, page.getOffset());
  }

  @Test
  public void should_clamp_limit_exceeding_max_to_100() {
    Page page = new Page(0, 200);

    assertEquals(100, page.getLimit());
  }

  @Test
  public void should_keep_limit_at_max_boundary() {
    Page page = new Page(0, 100);

    assertEquals(100, page.getLimit());
  }

  @Test
  public void should_use_default_limit_for_negative_values() {
    Page page = new Page(0, -1);

    assertEquals(20, page.getLimit());
  }

  @Test
  public void should_use_default_limit_for_zero() {
    Page page = new Page(0, 0);

    assertEquals(20, page.getLimit());
  }
}
