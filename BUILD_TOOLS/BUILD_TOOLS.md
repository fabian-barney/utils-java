# BUILD_TOOLS

Build-tool layer contract for dependency management, build reproducibility, and
artifact-generation workflow.

## Role in the Ruleset
- BUILD_TOOLS docs specialize how projects resolve dependencies, build
  artifacts, and enforce reproducible tooling behavior.
- Build-tool guidance inherits cross-cutting/language constraints and applies
  architecture/framework constraints where relevant before adding tool-specific
  workflow rules.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
BUILD_TOOLS includes:
- Package/dependency manager behavior and lock-file strategy.
- Build lifecycle, wrapper/runtime pinning, and deterministic installs.
- Tool-specific CI integration constraints for dependency/build phases.

BUILD_TOOLS does not include:
- Framework runtime architecture/lifecycle behavior.
- Library API usage patterns.
- Deployment/runtime infrastructure behavior.

Those belong in `FRAMEWORK/**`, `LIBRARY/**`, `INFRASTRUCTURE/**`, and
`CI-CD/**`.

## Specialization Contract
- Prefer the standard build tool for the language/ecosystem unless a strong,
  explicit reason exists to deviate.
- Tool-specific docs may narrow parent guidance when tool semantics require it.
- Any narrowing/override of inherited guidance must be explicit and justified in
  the specialized tool doc.
- Tool docs must not weaken inherited security/compliance/reproducibility
  constraints unless an upstream rule explicitly allows that weakening.

## Files
- [BUN.md](BUN.md) - Bun package manager guidance.
- [GRADLE.md](GRADLE.md) - Gradle build and wrapper guidance.
- [MAVEN.md](MAVEN.md) - Maven build and wrapper guidance.
- [NPM.md](NPM.md) - Node.js package manager guidance (npm, Yarn, pnpm).

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep tool behavior in child tool docs.
- When adding a new build-tool doc, update this index and align dependencies in
  `CORE/RULE_DEPENDENCY_TREE.md`.
