# GRADLE

Guidance for AI agents implementing and reviewing Gradle builds.

## Scope
- Define Gradle-specific reproducibility, dependency/plugin, and CI controls.
- Apply this file to Gradle build scripts and multi-module build design.

## Semantic Dependencies
- Inherit build-layer baseline from `BUILD_TOOLS/BUILD_TOOLS.md`.
- Inherit security/compliance constraints from
  `SECURITY/SECURITY.md` and `COMPLIANCE/LICENSES.md`.
- Inherit CI expectations from `CI-CD/CI-CD.md`.

## Defaults
- Use Gradle Wrapper (`gradlew`, `gradlew.bat`, `gradle/wrapper/*`).
- Pin plugin and dependency versions explicitly.
- Prefer Kotlin DSL for new builds unless project standard is Groovy DSL.
- Keep build scripts declarative and deterministic.

## Dependency and Plugin Management
- Centralize versions via version catalogs or clear convention mechanism.
- Avoid dynamic versions (`latest.release`, `+`) in production builds.
- Keep plugin management explicit.
- Keep dependency constraints clear in multi-module projects.

## Task and Build Logic Design
- Keep custom tasks small and deterministic.
- Avoid heavy imperative logic in build scripts when plugins/conventions are
  more maintainable.
- Isolate reusable build logic into convention plugins where appropriate.
- Keep task inputs/outputs declared for caching correctness.

## Performance and Caching
- Use configuration cache/build cache intentionally and keep builds compatible.
- Avoid tasks with hidden side effects that break caching.
- Keep build scans/metrics to identify bottlenecks in CI.
- Keep parallelism settings explicit and environment-aware.

## Multi-Module Governance
- Keep module boundaries explicit and acyclic.
- Avoid broad allprojects/subprojects mutation patterns that hide coupling.
- Keep dependency direction aligned with architecture boundaries.

## Security and Supply Chain
- Use trusted repositories and explicit repository declarations.
- Keep credentials out of committed files.
- Scan dependencies/plugins for vulnerabilities and license compliance.
- Treat custom plugin code as production code for review/security.

## VCS Ignore Additions
Add these when using Gradle (if not already covered by baseline ignores):
- `.gradle/`
- `build/`

Do not ignore wrapper scripts or wrapper JAR required for builds.

## High-Risk Pitfalls
1. Building with system Gradle instead of wrapper.
2. Dynamic dependency/plugin versions causing drift.
3. Hidden task side effects breaking cache reproducibility.
4. Global subprojects mutations creating fragile coupling.
5. Credentials committed in gradle properties/settings.
6. Cyclic module dependencies hidden in large builds.

## Do / Don't Examples
### 1. Wrapper Usage
```text
Don't: gradle build
Do:    ./gradlew build
```

### 2. Version Stability
```text
Don't: implementation("com.example:lib:+")
Do:    implementation("com.example:lib:1.4.2")
```

### 3. Task Determinism
```text
Don't: task reads undeclared env/files and writes random output path.
Do:    task declares inputs/outputs and deterministic behavior.
```

## Code Review Checklist for Gradle
- Is wrapper used and maintained?
- Are dependency/plugin versions deterministic?
- Is build logic maintainable and side-effect controlled?
- Are module boundaries and dependency direction clear?
- Are cache/performance settings compatible with task behavior?
- Are repository credentials and supply-chain controls secure?

## Testing Guidance
- Run wrapper-based clean build in CI.
- Run dependency/conflict checks on version changes.
- Validate configuration-cache/build-cache compatibility for key tasks.
- Test multi-module builds for boundary regressions.
- Run vulnerability/license checks for dependency/plugin updates.

## Override Notes
- Project convention plugins may add stricter rules, but wrapper usage,
  deterministic versioning, and secure supply-chain controls remain mandatory.
