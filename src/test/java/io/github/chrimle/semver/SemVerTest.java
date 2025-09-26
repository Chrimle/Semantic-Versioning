package io.github.chrimle.semver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SemVerTest {

  @Nested
  class MajorTests {

    @Test
    void testNegativeThrows() {
      final var exception =
          assertThrows(IllegalArgumentException.class, () -> new SemVer(-1, 0, 0));
      assertEquals("`major` MUST be at least `0`", exception.getMessage());
    }

    @Test
    void testIncrement() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementMajor();
      assertEquals(1, updatedSemVer.major());
    }

    @Test
    void testIncrementWithChange() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementVersion(Change.MAJOR);
      assertEquals(1, updatedSemVer.major());
    }
  }

  @Nested
  class MinorTests {

    @Test
    void testNegativeThrows() {
      final var exception =
          assertThrows(IllegalArgumentException.class, () -> new SemVer(0, -1, 0));
      assertEquals("`minor` MUST be at least `0`", exception.getMessage());
    }

    @Test
    void testIncrement() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementMinor();
      assertEquals(1, updatedSemVer.minor());
    }

    @Test
    void testIncrementWithChange() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementVersion(Change.MINOR);
      assertEquals(1, updatedSemVer.minor());
    }
  }

  @Nested
  class PatchTests {

    @Test
    void testNegativeThrows() {
      final var exception =
          assertThrows(IllegalArgumentException.class, () -> new SemVer(0, 0, -1));
      assertEquals("`patch` MUST be at least `0`", exception.getMessage());
    }

    @Test
    void testIncrement() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementPatch();
      assertEquals(1, updatedSemVer.patch());
    }

    @Test
    void testIncrementWithChange() {
      final var semVer = new SemVer(0, 0, 0);
      final var updatedSemVer = semVer.incrementVersion(Change.PATCH);
      assertEquals(1, updatedSemVer.patch());
    }
  }

  @Nested
  class ToCompleteVersionStringTests {
    @Test
    void testMajor() {
      final var semVer = new SemVer(1, 0, 0);
      assertEquals("v1.0.0", semVer.toCompleteVersionString());
    }

    @Test
    void testMajorAndMinor() {
      final var semVer = new SemVer(1, 2, 0);
      assertEquals("v1.2.0", semVer.toCompleteVersionString());
    }

    @Test
    void testMajorAndMinorAndPatch() {
      final var semVer = new SemVer(1, 2, 3);
      assertEquals("v1.2.3", semVer.toCompleteVersionString());
    }
  }

  @Nested
  class ToShortVersionStringTests {

    @Test
    void testMajor() {
      final var semVer = new SemVer(1, 0, 0);
      assertEquals("v1", semVer.toShortVersionString());
    }

    @Test
    void testMajorAndMinor() {
      final var semVer = new SemVer(1, 2, 0);
      assertEquals("v1.2", semVer.toShortVersionString());
    }

    @Test
    void testMajorAndMinorAndPatch() {
      final var semVer = new SemVer(1, 2, 3);
      assertEquals("v1.2.3", semVer.toShortVersionString());
    }
  }

  @Nested
  class CompareToTests {
    @Test
    void testNull() {
      final var semVer = new SemVer(1, 2, 3);
      assertThrows(IllegalArgumentException.class, () -> semVer.compareTo(null));
    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    void testSelf() {
      final var self = new SemVer(1, 2, 3);
      assertEquals(0, self.compareTo(self));
    }

    @Test
    void testEqual() {
      final var left = new SemVer(1, 2, 3);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), right.compareTo(left));
      assertEquals(0, left.compareTo(right));
      assertEquals(0, right.compareTo(left));
    }

    @Test
    void testUnequalMajor() {
      final var left = new SemVer(3, 2, 3);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), -right.compareTo(left));
      assertEquals(1, left.compareTo(right));
      assertEquals(-1, right.compareTo(left));
    }

    @Test
    void testUnequalMinor() {
      final var left = new SemVer(1, 3, 3);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), -right.compareTo(left));
      assertEquals(1, left.compareTo(right));
      assertEquals(-1, right.compareTo(left));
    }

    @Test
    void testUnequalPatch() {
      final var left = new SemVer(1, 2, 4);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), -right.compareTo(left));
      assertEquals(1, left.compareTo(right));
      assertEquals(-1, right.compareTo(left));
    }
  }
}
