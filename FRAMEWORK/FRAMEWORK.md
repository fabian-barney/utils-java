# FRAMEWORK

Framework-layer contract for runtime and UI/application framework behavior.

## Role in the Ruleset
- FRAMEWORK docs specialize language and architecture rules for concrete
  framework ecosystems.
- Framework docs inherit cross-cutting and language baselines, and apply
  architecture constraints where relevant.
- Architecture and framework guidance are peers in the same semantic layer;
  conflicts require explicit specialization/override rationale.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
FRAMEWORK includes:
- Framework lifecycle and state-management guidance.
- Framework-specific performance/testing patterns and runtime behavior
  (including rendering/SSR/hydration where applicable).
- Framework-specific pitfalls and code review checklists.

FRAMEWORK does not include:
- Generic language conventions and readability rules.
- Generic library API usage rules.
- Build/deployment/CI implementation details.

Those belong in `LANGUAGE/**`, `LIBRARY/**`, `BUILD_TOOLS/**`,
`INFRASTRUCTURE/**`, and `CI-CD/**`.

## Specialization Contract
- Framework docs may narrow language-level guidance when framework semantics
  require it.
- Any framework-level override must be explicit and justified in the framework
  document.
- Framework docs must not silently weaken cross-cutting security/compliance/
  testing constraints.

## Selection Guidance
- Prefer mature, enterprise-ready frameworks with a proven track record.
- Popularity is a strong indicator; widely adopted frameworks are more likely to
  be maintained and supported long term.
- Prefer trustworthy stewardship (e.g., Apache Foundation, Linux Foundation,
  major vendors, or well-governed communities) over one-person side projects.
- Avoid adopting very new frameworks without evidence of long-term sustainability.
- Favor clear release cadences, published support windows, and strong maintenance.
- Prefer comprehensive feature sets over hype-driven adoption.
- Ensure licenses are compatible with commercial closed-source use and that
  required attribution is provided; see `COMPLIANCE/LICENSES.md`.

## Files
- [ANGULAR.md](ANGULAR.md) - Angular framework guidance.
- [PRIMEFACES.md](PRIMEFACES.md) - PrimeFaces UI framework guidance.
- [REACT.md](REACT.md) - React framework guidance.
- [SPRING_BOOT.md](SPRING_BOOT.md) - Spring Boot guidance.
- [SVELTE.md](SVELTE.md) - Svelte framework guidance.
- [TAILWIND_CSS.md](TAILWIND_CSS.md) - Tailwind CSS guidance.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep framework behavior in child framework documents.
- When adding a new framework doc, update this index and align semantic
  dependencies in `CORE/RULE_DEPENDENCY_TREE.md`.
