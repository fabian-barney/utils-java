# JAVA

## Entry Point
- `EFFECTIVE_JAVA.md` - distilled ruleset for Java code quality.

## General Java Rules (summary)
- Prefer immutability where practical.
- Avoid exposing internal mutable state.
- Use builder patterns for complex construction.
- Favor interfaces over implementations in APIs.
- Fail fast with clear exceptions.

## VCS Ignore Additions
Add these when using Java build tools (if not already covered by the baseline ignore list):
- `target/`, `build/`
- `*.class`, `*.jar`, `*.war`, `*.ear`
- `pom.xml.tag`, `pom.xml.releaseBackup`, `pom.xml.versionsBackup`, `pom.xml.next`
- `release.properties`, `dependency-reduced-pom.xml`, `buildNumber.properties`
- `.gradle/`

Do not ignore wrapper scripts or jars that are required to build the project.
