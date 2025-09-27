package io.github.chrimle.semver;

import org.apiguardian.api.API;

/**
 * The <em>type</em> of change in <a href="https://semver.org/">Semantic Versioning</a>.
 *
 * @since 1.0.0
 * @author Chrimle
 */
@API(status = API.Status.STABLE, since = "1.0.1")
public enum Change {
  /**
   * <strong>MAJOR</strong> change.
   *
   * <p><em>... when you make incompatible API changes.</em>
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  MAJOR,
  /**
   * <strong>MINOR</strong> change.
   *
   * <p><em>... when you add functionality in a backward compatible manner.</em>
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  MINOR,
  /**
   * <strong>PATCH</strong> change.
   *
   * <p><em>... when you make backward compatible bug fixes.</em>
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  PATCH
}
