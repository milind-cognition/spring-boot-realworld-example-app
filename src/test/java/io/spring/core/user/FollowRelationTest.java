package io.spring.core.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FollowRelationTest {

  @Test
  public void should_create_follow_relation() {
    FollowRelation relation = new FollowRelation("user-1", "user-2");

    assertEquals("user-1", relation.getUserId());
    assertEquals("user-2", relation.getTargetId());
  }

  @Test
  public void should_have_equality_based_on_all_fields() {
    FollowRelation relation1 = new FollowRelation("user-1", "user-2");
    FollowRelation relation2 = new FollowRelation("user-1", "user-2");

    assertEquals(relation1, relation2);
    assertEquals(relation1.hashCode(), relation2.hashCode());
  }

  @Test
  public void should_not_be_equal_for_different_user_ids() {
    FollowRelation relation1 = new FollowRelation("user-1", "user-2");
    FollowRelation relation2 = new FollowRelation("user-3", "user-2");

    assertNotEquals(relation1, relation2);
  }

  @Test
  public void should_not_be_equal_for_different_target_ids() {
    FollowRelation relation1 = new FollowRelation("user-1", "user-2");
    FollowRelation relation2 = new FollowRelation("user-1", "user-3");

    assertNotEquals(relation1, relation2);
  }
}
