import org.jspecify.annotations.NullMarked;

/**
 * Provides classes for representing, comparing and interacting with Semantic Versions.
 *
 * <p>This module implements the <a href="https://semver.org">Semantic Versioning</a> specification.
 * It allows to easily create a <i>semver</i>-representation, extract major/minor/patch numbers,
 * perform strict version comparisons and handling new release versions based on <i>changes</i>.
 *
 * <h2>Quick Start</h2>
 *
 * <p>The primary entry point for this module is the {@code SemVer} class:
 *
 * <pre>{@code
 * SemVer v0_1_0 = new SemVer(0, 1, 0);
 * v0_1_0.isStable(); // returns `false` because MAJOR == 0
 *
 * // Creates a new SemVer-instance of version 1.0.0
 * SemVer v1_0_0 = v0_1_0.incrementVersion(Change.MAJOR);
 *
 * v1_0_0.toShortVersionString(); // returns `"v1"`
 * v1_0_0.toCompleteVersionString() // returns `"v1.0.0"`
 *
 * // Creates a new SemVer-instance.
 * // Equivalent to `incrementVersion(Change.MINOR)`
 * SemVer v1_1_0 = v1_0_0.incrementMinor();
 *
 * v1_1_0.toShortVersionString(); // returns `"v1.1"`
 * v1_1_0.toCompleteVersionString() // returns `"v1.1.0"`
 * }</pre>
 *
 * @see <a href="https://semver.org">Official Semantic Versioning Specification</a>
 * @see <a href="https://github.com/Chrimle/Semantic-Versioning">This Semantic-Versioning Project on
 *     GitHub</a>
 */
@NullMarked
module io.github.chrimle.semver {
  // Exports
  exports io.github.chrimle.semver;

  // Requires (non-static)
  requires io.github.chrimle.exceptionfactory;

  // Requires (static)
  requires static org.apiguardian.api;
  requires static org.jetbrains.annotations;
  requires static org.jspecify;
}
