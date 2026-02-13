# PLAN

Guidance for AI agents creating implementation plans.

## Scope
- Define planning standards for multi-step or high-risk tasks.
- Apply this file when producing execution plans before implementation.

## Semantic Dependencies
- Inherit dependency order from `CORE/RULE_DEPENDENCY_TREE.md`.
- Inherit testing/security/compliance constraints from
  `TEST/TEST.md`, `SECURITY/SECURITY.md`, and `COMPLIANCE/COMPLIANCE.md`.
- Inherit workflow constraints from `CORE/VERSION_CONTROL_SYSTEM.md`.

## When Planning Is Required
- Multi-document or multi-repo changes.
- High-risk refactors and behavior changes.
- Work spanning multiple semantic layers
  (language/framework/library/infrastructure/CI-CD).
- Any change with unclear requirements or significant rollback risk.

## Decision-Complete Plan Requirements
A plan must specify:
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

## Risk and Dependency Handling
- Identify external dependencies and blockers early.
- Call out coupling across layers and documents.
- Prefer dependency-first ordering (parent constraints before child
  specialization).
- Include rollback options for high-impact changes.

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
- Task-specific user constraints may change execution order, but plans must stay
  decision-complete and dependency-aware.
