# PLAN

Guidance for AI agents creating implementation plans.

## Scope
- Define planning standards for implementation tasks.
- Apply this file before implementation for every implementation concern.
- Use deeper, decision-complete planning for multi-step or high-risk work.

## Semantic Dependencies
- Inherit dependency order from `CORE/RULE_DEPENDENCY_TREE.md`.
- Resolve planning constraints through relevant index docs:
  `LANGUAGE/LANGUAGE.md`, `DESIGN/DESIGN.md`,
  `ARCHITECTURE/ARCHITECTURE.md`, `FRAMEWORK/FRAMEWORK.md`,
  `LIBRARY/LIBRARY.md`, `BUILD_TOOLS/BUILD_TOOLS.md`,
  `INFRASTRUCTURE/INFRASTRUCTURE.md`, and `CI-CD/CI-CD.md`.
- Apply corresponding specialized leaf rules under `LANGUAGE/**`,
  `DESIGN/**`, `ARCHITECTURE/**`, `FRAMEWORK/**`, `LIBRARY/**`,
  `BUILD_TOOLS/**`, `INFRASTRUCTURE/**`, and `CI-CD/**` when they affect
  planning decisions and risk.
- Inherit testing/security/compliance constraints from
  `TEST/TEST.md`, `SECURITY/SECURITY.md`, and `COMPLIANCE/COMPLIANCE.md`.
- Inherit workflow constraints from `CORE/VERSION_CONTROL_SYSTEM.md`.

## Planning Requirement (Mandatory)
- Create a plan before starting implementation for every implementation task.
- The plan may be lightweight for low-risk, trivial changes, but it must still
  be decision-complete for its scope and risk while stating required elements
  tersely.
- Do not start implementation when no plan exists.

## Decision-Complete Plan Requirements
A plan must specify (at depth appropriate to scope/risk):
- Goal and success criteria.
- In-scope and out-of-scope items.
- Semantic dependency order and target files.
- Key design decisions with rationale and chosen defaults.
- Edge cases/failure modes and mitigation strategy.
- Verification strategy (tests/checks) and acceptance criteria.
- Rollout/rollback or migration steps where relevant.

## Plan Quality Rules
- Keep steps concrete and implementable.
- Avoid ambiguous TODO-style phrasing.
- Keep assumptions explicit; avoid hidden decisions.
- Mark where follow-up issues are needed.
- Keep plan aligned with one-issue/one-branch/one-PR workflow when required.
- Prioritize system-level architecture and design decisions in planning.
- Do not over-index on fine-grained implementation details that are better
  handled during execution unless they materially change risk/scope.

## Risk and Dependency Handling
- Identify external dependencies and blockers early.
- Call out coupling across layers and documents.
- Prefer dependency-first ordering (parent constraints before child
  specialization).
- Include rollback options for high-impact changes.

## Research Requirements
- Perform intensive research before finalizing a plan.
- Research must cover:
  - semantic parent and sibling docs that influence scope and decisions,
  - architecture and design constraints,
  - relevant codebase context and issue/PR history,
  - external authoritative sources when domain or risk requires it.
- Record the key options considered and why the chosen plan is preferred.

## Testing and Validation Planning
- Define required tests before implementation starts.
- Include regression strategy for changed behavior.
- Specify mandatory CI checks and quality gates.
- Define observable acceptance signals for rollout.

## High-Risk Pitfalls
1. Plans that leave critical decisions to implementation time.
2. Ignoring semantic dependency order.
3. Missing verification criteria or measurable "done" definition.
4. Oversized steps with hidden complexity.
5. No rollback strategy for risky changes.
6. Untracked assumptions that later break execution.
7. Shallow research that misses better options or known constraints.

## Do / Don't Examples
### 1. Step Quality
```text
Don't: "Update docs and fix related stuff"
Do:    "Rewrite LANGUAGE/JAVA/JAVA.md with sections A/B/C; add tests X/Y"
```

### 2. Verification Clarity
```text
Don't: "Run tests"
Do:    "Run markdownlint for touched docs and report pass/fail output"
```

### 3. Dependency Order
```text
Don't: update framework child docs before parent language constraints.
Do:    update parent baselines first, then child specializations.
```

## Plan Review Checklist
- Is the plan decision-complete and implementation-ready?
- Are scope and success criteria explicit?
- Are semantic dependencies and ordering correct?
- Are risks, mitigations, and rollback steps captured?
- Are testing and acceptance criteria concrete?
- Are assumptions and follow-ups explicit?

## Override Notes
- Task-specific user constraints may change plan format or depth, but they do
  not remove the mandatory plan-before-implementation requirement.
- Plans must stay decision-complete and dependency-aware.
