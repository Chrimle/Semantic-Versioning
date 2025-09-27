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
   * <strong>Definition</strong>
   *
   * <blockquote>
   *
   * <em>"MAJOR version when you make incompatible API changes."</em>
   *
   * <p><a href="https://semver.org/#summary">Semantic Versioning: Summary</a>
   *
   * </blockquote>
   *
   * @since 1.0.0
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  MAJOR,
  /**
   * <strong>Definition</strong>
   *
   * <blockquote>
   *
   * <em>"MINOR version when you add functionality in a backward compatible manner."</em>
   *
   * <p><a href="https://semver.org/#summary">Semantic Versioning: Summary</a>
   *
   * </blockquote>
   *
   * @since 1.0.0
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  MINOR,
  /**
   * <strong>Definition</strong>
   *
   * <blockquote>
   *
   * <em>"PATCH version when you make backward compatible bug fixes."</em>
   *
   * <p><a href="https://semver.org/#summary">Semantic Versioning: Summary</a>
   *
   * </blockquote>
   *
   * @since 1.0.0
   */
  @API(status = API.Status.STABLE, since = "1.2.0")
  PATCH
}
