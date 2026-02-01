# MAVEN

Guidance for Apache Maven builds.

## General
- Use the Maven Wrapper (`mvnw`, `mvnw.cmd`, `.mvn/wrapper/*`) for reproducible builds.
- Pin plugin and dependency versions explicitly.

## VCS Ignore Additions
Add these when using Maven (if not already covered by the baseline ignore list):
- `target/`
- `*.class`, `*.jar`, `*.war`, `*.ear`
- `pom.xml.tag`, `pom.xml.releaseBackup`, `pom.xml.versionsBackup`, `pom.xml.next`
- `release.properties`, `dependency-reduced-pom.xml`, `buildNumber.properties`
- `.mvn/timing.properties`

Do not ignore wrapper scripts or the wrapper JAR required to build the project.
