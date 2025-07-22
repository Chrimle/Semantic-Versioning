package io.github.chrimle.semver;

/**
 * The <em>type</em> of change in <a href="https://semver.org/">Semantic Versioning</a>.
 *
 * @since 1.0.0
 * @author Chrimle
 */
public enum Change {
  /**
   * <strong>MAJOR</strong> change.
   *
   * <p><em>... when you make incompatible API changes.</em>
   */
  MAJOR,
  /**
   * <strong>MINOR</strong> change.
   *
   * <p><em>... when you add functionality in a backward compatible manner.</em>
   */
  MINOR,
  /**
   * <strong>PATCH</strong> change.
   *
   * <p><em>... when you make backward compatible bug fixes.</em>
   */
  PATCH
}
