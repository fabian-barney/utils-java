# GRADLE

Guidance for Gradle builds.

## General
- Use the Gradle Wrapper (`gradlew`, `gradlew.bat`, `gradle/wrapper/*`) for reproducible builds.
- Pin plugin and dependency versions explicitly.

## VCS Ignore Additions
Add these when using Gradle (if not already covered by the baseline ignore list):
- `.gradle/`
- `build/`

Do not ignore wrapper scripts or the wrapper JAR required to build the project.
