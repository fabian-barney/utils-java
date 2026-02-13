# RULE_DEPENDENCY_TREE

Canonical semantic dependency and precedence guidance for this repository's
AI-agent rule documents.

## Purpose
- Define how rules inherit, specialize, and override each other.
- Keep guidance deep and actionable without contradictory duplication.
- Provide a stable ordering for issue/branch/PR execution across documents.

## Scope
- Applies to the non-meta rules corpus referenced by `AI.md`.
- Excludes `AI-RULES/**` (meta/governance for maintaining this repository).
- Excludes user/governance docs such as `README.md`, `CONTRIBUTING.md`,
  `CHANGELOG.md`, and `AGENTS_TEMPLATE.md`.

## Core Principles
- Semantic dependencies are authoritative; existing markdown links are not.
- More specific scope may override broader scope only when the override is
  explicit and justified.
- Safety and correctness constraints from broader scope remain mandatory unless
  the broader scope itself marks them as optional.
- Intermediate technical docs (for example language base docs) must be complete
  rule sets for their own scope, not thin index stubs.

## Precedence Model
Apply rules from top to bottom. Lower layers specialize higher layers.

1. Cross-cutting baseline
2. Language and data-format baseline
3. Architecture and framework baseline
4. Library and build-tool baseline
5. Infrastructure and CI/CD baseline
6. Task overlays (`PROGRAMMING`, `REVIEW`, `PLAN`)

### Layer Details
#### 1) Cross-cutting baseline
Always inherited first:
- `CORE/LOGGING.md`
- `CORE/VERSION_CONTROL_SYSTEM.md`
- `SECURITY/SECURITY.md`
- `TEST/TEST.md`
- `LANGUAGE/CONVENTIONS.md`
- `LANGUAGE/READABILITY.md`
- `DESIGN/CLEAN_CODE.md`
- `DESIGN/SOLID.md`
- `COMPLIANCE/LICENSES.md`

#### 2) Language and format baseline
Defines syntax- and platform-level behavior:
- `LANGUAGE/JAVASCRIPT/JAVASCRIPT.md` (parent for TypeScript and JS frameworks)
- `LANGUAGE/TYPESCRIPT/TYPESCRIPT.md` (specializes JavaScript)
- `LANGUAGE/JAVA/JAVA.md` and `LANGUAGE/JAVA/EFFECTIVE_JAVA.md`
- `LANGUAGE/HTML/HTML.md`, `LANGUAGE/CSS/CSS.md`
- `LANGUAGE/SQL/SQL.md`
- `LANGUAGE/YAML/YAML.md`
- `LANGUAGE/SHELL/SHELL.md`

#### 3) Architecture and framework baseline
Defines application/system style and framework-specific behavior:
- `ARCHITECTURE/**`
- `FRAMEWORK/**`

#### 4) Library and build-tool baseline
Defines specialized APIs and tool workflow constraints:
- `LIBRARY/**`
- `BUILD_TOOLS/**`

#### 5) Infrastructure and CI/CD baseline
Defines environment, deployment, and delivery behavior:
- `INFRASTRUCTURE/**`
- `CI-CD/**`

#### 6) Task overlays
Apply based on task type, not technology:
- `PROGRAMMING/PROGRAMMING.md`
- `REVIEW/CODE_REVIEW.md`
- `PLAN/PLAN.md`

## Document Types and Depth Contract
### Pure Index Docs
These primarily route to child docs and define boundary/selection guidance:
- `AI.md`
- `CORE/CORE.md`
- `LANGUAGE/LANGUAGE.md`
- `FRAMEWORK/FRAMEWORK.md`
- `LIBRARY/LIBRARY.md`
- `BUILD_TOOLS/BUILD_TOOLS.md`
- `ARCHITECTURE/ARCHITECTURE.md`
- `INFRASTRUCTURE/INFRASTRUCTURE.md`
- `CI-CD/CI-CD.md`
- `DESIGN/DESIGN.md`
- `REVIEW/REVIEW.md`
- `COMPLIANCE/COMPLIANCE.md`

Pure index docs should remain concise and must not duplicate deep, leaf-level
implementation details.

### Deep Technical Docs
All technical docs that define coding/architecture behavior, including
intermediate parents, must be deep and operational.

Examples (non-exhaustive):
- Language parents: `LANGUAGE/JAVASCRIPT/JAVASCRIPT.md`,
  `LANGUAGE/JAVA/JAVA.md`, `LANGUAGE/TYPESCRIPT/TYPESCRIPT.md`
- Framework docs: `FRAMEWORK/ANGULAR.md`, `FRAMEWORK/REACT.md`, etc.
- Architecture docs: `ARCHITECTURE/REST.md`, `ARCHITECTURE/GRAPHQL.md`, etc.
- Libraries and tools: `LIBRARY/*`, `BUILD_TOOLS/*`, `INFRASTRUCTURE/*`,
  `CI-CD/GITLAB.md`

Required sections for deep technical docs:
- Scope and applicability
- Semantic dependencies (upstream rules)
- Defaults and guardrails
- High-risk pitfalls/failure modes
- Do/Don't examples with explicit good/bad labeling
- Code review checklist
- Testing guidance
- Override notes where this doc narrows or changes parent guidance

## Semantic Dependency Chains (Current Repository)
### JavaScript/TypeScript/Web UI Chain
- `LANGUAGE/JAVASCRIPT/JAVASCRIPT.md`
- `LANGUAGE/TYPESCRIPT/TYPESCRIPT.md` (overrides JS where needed)
- Frameworks:
  - `FRAMEWORK/REACT.md`
  - `FRAMEWORK/ANGULAR.md`
  - `FRAMEWORK/SVELTE.md`
- Styling/UI layers:
  - `LANGUAGE/HTML/HTML.md`
  - `LANGUAGE/CSS/CSS.md`
  - `FRAMEWORK/TAILWIND_CSS.md`

### Java/Spring/Enterprise Chain
- `LANGUAGE/JAVA/JAVA.md`
- `LANGUAGE/JAVA/EFFECTIVE_JAVA.md`
- `FRAMEWORK/SPRING_BOOT.md`
- Java libraries:
  - `LIBRARY/JPA.md`, `LIBRARY/JOOQ.md`, `LIBRARY/JUNIT.md`,
    `LIBRARY/MOCKITO.md`, `LIBRARY/LOMBOK.md`, `LIBRARY/MAPSTRUCT.md`,
    `LIBRARY/JAXB.md`, `LIBRARY/GUAVA.md`, `LIBRARY/KAFKA.md`,
    `LIBRARY/RESILIENCE4J.md`

### Query/Data Access Chain
- `LANGUAGE/SQL/SQL.md`
- `ARCHITECTURE/N_PLUS_1.md`
- `ARCHITECTURE/CQRS.md`
- `LIBRARY/JPA.md`, `LIBRARY/JOOQ.md`

### Delivery/Runtime Chain
- `LANGUAGE/YAML/YAML.md` and `LANGUAGE/SHELL/SHELL.md`
- `BUILD_TOOLS/NPM.md`, `BUILD_TOOLS/BUN.md`, `BUILD_TOOLS/MAVEN.md`,
  `BUILD_TOOLS/GRADLE.md`
- `INFRASTRUCTURE/DOCKER.md`
- `INFRASTRUCTURE/KUBERNETES.md`
- `INFRASTRUCTURE/HELM.md`, `INFRASTRUCTURE/ISTIO.md`
- `CI-CD/GITLAB.md`

## Conflict Resolution Rules
When two rules seem to conflict, resolve in this order:
1. Check if one rule is from a more specific semantic layer.
2. Check whether the specific doc declares an explicit override.
3. If no explicit override exists, keep the broader rule and align the specific
   doc in a follow-up.
4. For security/compliance constraints, prefer stricter behavior by default.
5. If ambiguity remains, document the decision and create an explicit rule
   update issue.

## Redundancy and Override Policy
Allowed:
- Repeating a parent rule as a short reminder plus a clear parent reference.
- Narrowing a parent rule with framework/library-specific constraints.

Not allowed:
- Silent contradictions against parent docs.
- Re-defining cross-cutting baseline rules with weaker language.
- Copy-pasting large parent sections without specialization.

When overriding, add a short "override rationale" sentence with:
- what is overridden,
- why the specialization is required,
- and what remains inherited.

## Authoring Checklist (Before Opening a PR)
- Are semantic parents identified correctly?
- Is this doc type correct (pure index vs deep technical)?
- If deep technical: are all required sections present?
- Are examples clearly marked good/bad?
- Are parent rules referenced instead of duplicated when no specialization is
  needed?
- Are any overrides explicit, justified, and bounded?

## Code Review Checklist (Dependency Integrity)
- Does the PR preserve precedence order and semantic inheritance?
- Are new rules placed at the correct abstraction layer?
- Do specialized docs avoid weakening cross-cutting baseline constraints?
- Are required companion index updates included when adding new docs?
- Does the change introduce new semantic parents that require follow-up issues?

## Rollout Ordering Rule
Use root semantic order, not existing file links:
1. Define/adjust dependency model and parent index boundaries.
2. Upgrade cross-cutting foundations.
3. Upgrade language/formats.
4. Upgrade architecture/framework/tool/library docs.
5. Reconcile final root index (`AI.md`).

## Maintenance
- Update this file when adding a new technology domain or a new semantic layer.
- Keep this file aligned with `AI.md` and parent index docs.
- If dependency structure changes materially, create a dedicated issue and PR.
