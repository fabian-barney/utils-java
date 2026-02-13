# CORE

Core, non-negotiable baseline rules for all technology stacks in this
repository.

## Role in the Ruleset
- CORE is part of the first-applied cross-cutting baseline layer.
- CORE rules are inherited by all downstream docs, including language,
  framework, library, build-tool, infrastructure, CI/CD, and task-overlay docs.
- Precedence and override behavior are defined in
  [RULE_DEPENDENCY_TREE.md](RULE_DEPENDENCY_TREE.md).

## Scope Boundary
CORE includes only stack-agnostic guidance such as:
- VCS workflow and delivery hygiene.
- Logging safety and reliability practices.
- Semantic dependency and override contract for the full ruleset.

CORE does not include:
- Language-specific coding rules.
- Framework-specific implementation details.
- Library/tool/runtime-specific usage patterns.

Those belong in their respective domains (`LANGUAGE/**`, `FRAMEWORK/**`,
`LIBRARY/**`, `BUILD_TOOLS/**`, `INFRASTRUCTURE/**`, `CI-CD/**`).

## Application Contract for AI Agents
- Apply CORE guidance by default before any domain-specific specialization.
- Do not weaken CORE constraints in downstream docs unless an explicit override
  is documented and justified in the specialized doc.
- If a downstream rule conflicts with CORE and no explicit override exists,
  treat CORE as authoritative and raise a follow-up issue.

## Files
- [VERSION_CONTROL_SYSTEM.md](VERSION_CONTROL_SYSTEM.md) - Commit/branch/PR workflow, issue linkage, and VCS hygiene.
- [LOGGING.md](LOGGING.md) - Logging purpose, safety, and reliability guardrails.
- [RULE_DEPENDENCY_TREE.md](RULE_DEPENDENCY_TREE.md) - Semantic inheritance, precedence, and override contract.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Add new core documents only when guidance is truly cross-cutting.
- Any dependency-precedence change must be reflected in
  `RULE_DEPENDENCY_TREE.md`.
