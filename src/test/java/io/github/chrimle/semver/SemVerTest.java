/*
 * Copyright 2025 Chrimle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the License.
 */

package io.github.chrimle.semver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void testIncrementAtMaxInteger() {
      final var semVer = new SemVer(Integer.MAX_VALUE, 0, 0);
      final var exception = assertThrows(ArithmeticException.class, semVer::incrementMajor);
      assertTrue(exception.getMessage().contains("major"));
    }

    @Test
    void testIncrementWithChangeAtMaxInteger() {
      final var semVer = new SemVer(Integer.MAX_VALUE, 0, 0);
      final var exception =
          assertThrows(ArithmeticException.class, () -> semVer.incrementVersion(Change.MAJOR));
      assertTrue(exception.getMessage().contains("major"));
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

    @Test
    void testIncrementAtMaxInteger() {
      final var semVer = new SemVer(0, Integer.MAX_VALUE, 0);
      final var exception = assertThrows(ArithmeticException.class, semVer::incrementMinor);
      assertTrue(exception.getMessage().contains("minor"));
    }

    @Test
    void testIncrementWithChangeAtMaxInteger() {
      final var semVer = new SemVer(0, Integer.MAX_VALUE, 0);
      final var exception =
          assertThrows(ArithmeticException.class, () -> semVer.incrementVersion(Change.MINOR));
      assertTrue(exception.getMessage().contains("minor"));
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

    @Test
    void testIncrementAtMaxInteger() {
      final var semVer = new SemVer(0, 0, Integer.MAX_VALUE);
      final var exception = assertThrows(ArithmeticException.class, semVer::incrementPatch);
      assertTrue(exception.getMessage().contains("patch"));
    }

    @Test
    void testIncrementWithChangeAtMaxInteger() {
      final var semVer = new SemVer(0, 0, Integer.MAX_VALUE);
      final var exception =
          assertThrows(ArithmeticException.class, () -> semVer.incrementVersion(Change.PATCH));
      assertTrue(exception.getMessage().contains("patch"));
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
    @SuppressWarnings({"EqualsWithItself", "SelfComparison"})
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

    @ParameterizedTest
    @CsvSource(
        value =
            """
            2,2,3
            1,3,3
            1,2,4
            2,0,0
            1,3,0
            """)
    void testLarger(final int major, final int minor, final int patch) {
      final var left = new SemVer(major, minor, patch);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), -right.compareTo(left));
      assertEquals(1, left.compareTo(right));
      assertEquals(-1, right.compareTo(left));
    }

    @ParameterizedTest
    @CsvSource(
        value =
            """
            0,2,3
            1,1,3
            1,2,2
            0,9,9
            1,1,9
            """)
    void testSmaller(final int major, final int minor, final int patch) {
      final var left = new SemVer(major, minor, patch);
      final var right = new SemVer(1, 2, 3);
      assertEquals(left.compareTo(right), -right.compareTo(left));
      assertEquals(-1, left.compareTo(right));
      assertEquals(1, right.compareTo(left));
    }
  }
}
