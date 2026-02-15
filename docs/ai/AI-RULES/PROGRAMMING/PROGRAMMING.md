# PROGRAMMING

Guidance for AI agents executing implementation tasks.

## Scope
- Define end-to-end implementation workflow expectations for coding tasks.
- Apply this file when making code changes (not pure planning/review-only tasks).

## Semantic Dependencies
- Inherit workflow and VCS constraints from `CORE/VERSION_CONTROL_SYSTEM.md`.
- Inherit testing constraints from `TEST/TEST.md`.
- Inherit security/compliance constraints from `SECURITY/SECURITY.md` and
  `COMPLIANCE/COMPLIANCE.md`.
- Inherit dependency order and full cross-cutting baseline from
  `CORE/RULE_DEPENDENCY_TREE.md`.
- Resolve constraints through the relevant index docs:
  `LANGUAGE/LANGUAGE.md`, `DESIGN/DESIGN.md`,
  `ARCHITECTURE/ARCHITECTURE.md`, `FRAMEWORK/FRAMEWORK.md`,
  `LIBRARY/LIBRARY.md`, `BUILD_TOOLS/BUILD_TOOLS.md`,
  `INFRASTRUCTURE/INFRASTRUCTURE.md`, and `CI-CD/CI-CD.md`.
- Apply corresponding specialized leaf rules under `LANGUAGE/**`,
  `DESIGN/**`, `ARCHITECTURE/**`, `FRAMEWORK/**`, `LIBRARY/**`,
  `BUILD_TOOLS/**`, `INFRASTRUCTURE/**`, and `CI-CD/**`.

## Default Execution Workflow
1. Verify hard preconditions before implementation:
   - A plan exists (see `PLAN/PLAN.md`).
   - The work is mapped to an issue/ticket.
   - Work is on a dedicated non-default branch for that issue/ticket.
   - If any precondition is missing, stop implementation and either establish
     the missing precondition first or, if impossible due to
     permissions/tooling/VCS policy, stop and report `BLOCKED` as defined in
     `CORE/VERSION_CONTROL_SYSTEM.md`.
2. Confirm behavior goals, acceptance criteria, and scope boundaries.
3. Locate semantic parent docs using `CORE/RULE_DEPENDENCY_TREE.md` and the
   relevant index docs (`LANGUAGE/LANGUAGE.md`, `DESIGN/DESIGN.md`,
   `ARCHITECTURE/ARCHITECTURE.md`, `FRAMEWORK/FRAMEWORK.md`,
   `LIBRARY/LIBRARY.md`, `BUILD_TOOLS/BUILD_TOOLS.md`,
   `INFRASTRUCTURE/INFRASTRUCTURE.md`, `CI-CD/CI-CD.md`).
4. Design minimal-change implementation path.
5. Implement with explicit error handling and observability where relevant.
6. Add/update tests and run verification.
7. Summarize changes, risks, and validation evidence.

## Implementation Quality Rules
- Keep changes scoped; avoid unrelated refactors.
- Prefer explicit, readable logic over compact clever solutions.
- Keep boundaries clear (domain vs transport vs infrastructure).
- Keep side effects explicit and controlled.
- Preserve backward compatibility unless change explicitly requires breakage.

## Dependency and Tooling Decisions
- Avoid new dependencies unless necessary and justified.
- Evaluate new dependencies using `FRAMEWORK/FRAMEWORK.md`,
  `LIBRARY/LIBRARY.md`, and `COMPLIANCE/LICENSES.md`.
- Keep runtime/build impact of dependency additions explicit.

## Verification Requirements
- Add tests for new behavior and bug fixes.
- Add regression tests before risky refactors where behavior is ambiguous.
- Run relevant checks locally/CI and report outcomes.
- If checks were not run, state why and expected risk.

## Delivery and Documentation
- Update user/developer docs when behavior or usage changes.
- Keep commit/PR summaries explicit about what changed and why.
- Document notable tradeoffs and deferred follow-ups.
- Include the completion status contract defined in
  `CORE/VERSION_CONTROL_SYSTEM.md`.

## High-Risk Pitfalls
1. Implementing without reading semantic parent rules.
2. Broad refactors bundled with feature changes.
3. Shipping behavior changes without tests.
4. Adding dependencies without compatibility/license review.
5. Silent security regressions due to missing boundary validation.
6. Incomplete change summaries hiding operational risk.

## Do / Don't Examples
### 1. Scope Control
```text
Don't: rewrite unrelated modules while fixing one endpoint bug.
Do:    keep fix scoped, create follow-up issue for broader refactor.
```

### 2. Verification
```text
Don't: merge behavior change with no tests or rationale.
Do:    add/adjust tests and report executed checks.
```

### 3. Dependency Introduction
```text
Don't: add convenience library without evaluation.
Do:    justify necessity, review license/security, and document impact.
```

## Code Review Checklist for Programming Tasks
- Does implementation align with semantic parent docs?
- Is change scope minimal and intentional?
- Are security/validation/error-handling boundaries correct?
- Are tests sufficient and relevant?
- Are dependency/tooling changes justified?
- Is change summary complete and actionable?

## Testing Guidance
- Follow full testing policy in `TEST/TEST.md`.
- Ensure changed behavior is covered by automated tests.
- Include negative/error-path tests for boundary logic.
- Include performance-sensitive checks where applicable.

## Override Notes
- Task-specific overlays (`PLAN/PLAN.md`, `REVIEW/CODE_REVIEW.md`) may adjust
  output format, but implementation safety/verification requirements here
  remain mandatory.
