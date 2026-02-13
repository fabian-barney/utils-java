# MAVEN

Guidance for AI agents implementing and reviewing Maven builds.

## Scope
- Define Maven-specific reproducibility, dependency, and lifecycle controls.
- Apply this file to Maven project configuration and CI build workflows.

## Semantic Dependencies
- Inherit build-layer baseline from `BUILD_TOOLS/BUILD_TOOLS.md`.
- Inherit security/compliance constraints from
  `SECURITY/SECURITY.md` and `COMPLIANCE/LICENSES.md`.
- Inherit CI gating expectations from `CI-CD/CI-CD.md`.

## Defaults
- Use Maven Wrapper (`mvnw`, `mvnw.cmd`, `.mvn/wrapper/*`) for reproducible
  builds.
- Keep plugin and dependency versions explicitly pinned.
- Keep build lifecycle predictable and avoid hidden side effects in profiles.
- Keep parent/BOM usage intentional and documented.

## Dependency and Plugin Management
- Centralize versions with `dependencyManagement`/BOM where appropriate.
- Avoid floating versions (`LATEST`, `RELEASE`, version ranges) in production
  builds.
- Keep plugin versions pinned; avoid implicit plugin-version resolution.
- Minimize duplicate transitive dependency paths and exclusions.

## Profile and Environment Rules
- Keep profiles explicit and purpose-driven.
- Avoid profiles that change core artifact semantics unexpectedly.
- Prefer environment-independent default build; use profiles for explicit
  deployment/runtime variations.
- Document required profile combinations for release builds.

## Reproducibility and CI
- Use wrapper in CI, not system Maven.
- Keep CI build flags deterministic (`-B`, optionally `-ntp` etc.).
- Ensure clean build paths for release (`clean verify`/project standard).
- Cache dependencies safely keyed by pom/wrapper/runtime version.

## Security and Supply Chain
- Use trusted artifact repositories and explicit mirror config.
- Keep repository credentials out of source-controlled settings.
- Enable dependency and plugin vulnerability/license checks in CI.
- Treat custom plugin executions as code with security review.

## VCS Ignore Additions
Add these when using Maven (if not already covered by baseline ignores):
- `target/`
- `*.class`, `*.war`, `*.ear`
- `pom.xml.tag`, `pom.xml.releaseBackup`, `pom.xml.versionsBackup`,
  `pom.xml.next`
- `release.properties`, `dependency-reduced-pom.xml`,
  `buildNumber.properties`
- `.mvn/timing.properties`

Do not ignore wrapper scripts or wrapper JARs required for build
(for example `.mvn/wrapper/maven-wrapper.jar`).
If a broader ignore pattern includes `*.jar`, add an explicit negation rule
for the wrapper JAR.

## High-Risk Pitfalls
1. Unpinned plugin versions causing non-deterministic builds.
2. Version ranges introducing drift.
3. Profile combinations altering artifact unexpectedly.
4. CI using system Maven instead of wrapper.
5. Credentials committed in settings files.
6. Transitive dependency conflicts unresolved until runtime.

## Do / Don't Examples
### 1. Version Pinning
```text
Don't: <version>[1.0,)</version>
Do:    explicit fixed version (or BOM-managed fixed set).
```

### 2. CI Invocation
```text
Don't: mvn test (system Maven in CI)
Do:    ./mvnw -B clean verify
```

### 3. Plugin Version
```text
Don't: plugin without explicit version.
Do:    plugin with pinned version in pluginManagement/build plugins.
```

## Code Review Checklist for Maven
- Is Maven Wrapper present and used in docs/CI?
- Are dependency/plugin versions pinned and auditable?
- Are profile effects explicit and controlled?
- Are repository/credential settings secure?
- Are license/security checks integrated?
- Are transitive conflicts and exclusions intentional?

## Testing Guidance
- Run clean wrapper-based build in CI and local reproducibility checks.
- Test profile-specific builds used in release/deploy workflows.
- Run dependency tree/conflict inspection for critical changes.
- Run vulnerability/license scan on dependency updates.

## Override Notes
- Project-specific release processes may add stricter Maven workflow rules, but
  wrapper usage, deterministic versioning, and supply-chain controls remain
  mandatory.
