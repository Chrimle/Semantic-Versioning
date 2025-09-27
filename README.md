# Semantic Versioning
*Simple Project for Semantic Version Strings in Java!*

## Import Dependency
```xml
<dependency>
    <groupId>io.github.chrimle</groupId>
    <artifactId>semantic-versioning</artifactId>
    <version>1.2.0</version>
</dependency>
```
### Available on...
- [Maven Central](https://central.sonatype.com/artifact/io.github.chrimle/semantic-versioning)
- [GitHub Packages](https://github.com/Chrimle/Semantic-Versioning/packages/)


## Example Usages

```java
import io.github.chrimle.semver.Change;
import io.github.chrimle.semver.SemVer;

static {
  var semVer_1_2_3 = new SemVer(1, 2, 3);

  // Prints "v1.2.3"
  System.out.println(semVer_1_2_3.toCompleteVersionString());

  var semVer_2_0_0 = semVer_1_2_3.incrementMajor();

  // Prints "v2"
  System.out.println(semVer_2_0_0.toShortVersionString());

  var semVer_2_1_0 = semVer_2_0_0.incrementVersion(Change.MINOR);

  // Prints "v2.1"
  System.out.println(semVer_2_1_0.toShortVersionString());
}
```
## JavaDocs
Refer to the [JavaDocs](https://javadoc.io/doc/io.github.chrimle/semantic-versioning/latest/index.html) for more information. 
