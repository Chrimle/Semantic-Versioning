package io.github.chrimle.semver;

import static io.github.chrimle.exceptionfactory.ExceptionFactory.illegalArgumentOf;
import static io.github.chrimle.exceptionfactory.MessageTemplates.OneArgTemplate.*;
import static io.github.chrimle.exceptionfactory.MessageTemplates.TwoArgTemplate.MUST_BE_AT_LEAST;

import io.github.chrimle.exceptionfactory.ExceptionBuilder;
import org.apiguardian.api.API;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a <em>Release Version Number</em> adhering to <a href="https://semver.org/">Semantic
 * Versioning</a>.
 *
 * @param major version. <em><strong>MUST NOT</strong></em> be negative.
 *     <em><strong>MAY</strong></em> be {@code 0} under <em>initial development</em>.
 * @param minor version. <em><strong>MUST NOT</strong></em> be negative.
 * @param patch version. <em><strong>MUST NOT</strong></em> be negative.
 * @since 1.0.0
 * @author Chrimle
 */
@API(status = API.Status.STABLE, since = "1.0.1")
public record SemVer(int major, int minor, int patch) implements Comparable<SemVer> {

  /**
   * Constructs a <em>valid</em> {@link SemVer} instance.
   *
   * @param major version.
   * @param minor version.
   * @param patch version.
   * @throws IllegalArgumentException if {@link #major} is less than {@code 0}.
   * @throws IllegalArgumentException if {@link #minor} is less than {@code 0}.
   * @throws IllegalArgumentException if {@link #patch} is less than {@code 0}.
   * @since 1.0.0
   */
  public SemVer {
    if (major < 0) throw illegalArgumentOf("major", MUST_BE_AT_LEAST, "0");
    if (minor < 0) throw illegalArgumentOf("minor", MUST_BE_AT_LEAST, "0");
    if (patch < 0) throw illegalArgumentOf("patch", MUST_BE_AT_LEAST, "0");
  }

  /**
   * Creates a new {@link SemVer} with the corresponding version incremented.
   *
   * @param change for determining the new {@code SemVer}.
   * @return the new {@code SemVer}.
   * @since 1.0.0
   * @throws IllegalArgumentException if {@code change} is {@code null}.
   */
  @Contract("null -> fail")
  public SemVer incrementVersion(final Change change) {
    if (change == null) throw illegalArgumentOf("change", MUST_NOT_BE_NULL);
    return switch (change) {
      case MAJOR -> incrementMajor();
      case MINOR -> incrementMinor();
      case PATCH -> incrementPatch();
    };
  }

  /**
   * Creates a new {@link SemVer} with the {@link #major}-version incremented.
   *
   * @since 1.0.0
   * @return the new {@code SemVer}.
   */
  @NotNull
  @Contract(" -> new")
  public SemVer incrementMajor() {
    return new SemVer(major + 1, 0, 0);
  }

  /**
   * Creates a new {@link SemVer} with the {@link #minor}-version incremented.
   *
   * @since 1.0.0
   * @return the new {@code SemVer}.
   */
  @NotNull
  @Contract(" -> new")
  public SemVer incrementMinor() {
    return new SemVer(major, minor + 1, 0);
  }

  /**
   * Creates a new {@link SemVer} with the {@link #patch}-version incremented.
   *
   * @since 1.0.0
   * @return the new {@code SemVer}.
   */
  @NotNull
  @Contract(" -> new")
  public SemVer incrementPatch() {
    return new SemVer(major, minor, patch + 1);
  }

  /**
   * Returns <em>this</em> {@link SemVer} as a {@code String} in the format: {@code
   * v{major}.{minor}.{patch}}.
   *
   * @return the formatted {@code String}.
   * @since 1.0.0
   */
  @Contract(pure = true)
  @NotNull
  public String toCompleteVersionString() {
    return "v%d.%d.%d".formatted(major, minor, patch);
  }

  /**
   * Returns <em>this</em> {@link SemVer} as a {@code String} in the format: {@code
   * v{major}.{minor}.{patch}} - where trailing zero versions are omitted.
   *
   * <p><strong>Examples:</strong>
   *
   * <ul>
   *   <li>{@code 1.0.0} returns {@code "v1"}
   *   <li>{@code 1.2.0} returns {@code "v1.2"}
   *   <li>{@code 1.2.3} returns {@code "v1.2.3"}
   * </ul>
   *
   * @return the formatted {@code String}.
   * @since 1.0.0
   */
  @NotNull
  public String toShortVersionString() {
    if (patch > 0) {
      return toCompleteVersionString();
    }
    if (minor > 0) {
      return "v%d.%d".formatted(major, minor);
    }
    return "v%d".formatted(major);
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code other} is {@code null}.
   * @since 1.1.0
   */
  @Override
  public int compareTo(final SemVer other) {
    if (other == null) {
      throw ExceptionBuilder.of(NullPointerException.class)
          .setMessage(MUST_NOT_BE_NULL, "other")
          .build();
    }
    int result = Integer.compare(this.major(), other.major());
    if (result != 0) {
      return result;
    }
    result = Integer.compare(this.minor(), other.minor());
    if (result != 0) {
      return result;
    }
    return Integer.compare(this.patch(), other.patch());
  }
}
