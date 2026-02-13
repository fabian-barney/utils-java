# REVIEW

Review-layer contract for validation and quality-gate guidance.

## Role in the Ruleset
- REVIEW is a task-overlay layer applied when the task is code/document review.
- Review guidance consumes cross-cutting, language, architecture, framework,
  and library constraints and operationalizes them into review checks.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
REVIEW includes:
- Review workflow expectations and reviewer output standards.
- Checklist-oriented verification guidance.
- Rules for handling findings, risk assessment, and unresolved items.

REVIEW does not include:
- Implementation-time coding instructions.
- Detailed framework/library usage guidance.
- Build/deployment runtime procedures.

Those belong in `PROGRAMMING/**`, `FRAMEWORK/**`, `LIBRARY/**`,
`BUILD_TOOLS/**`, `INFRASTRUCTURE/**`, and `CI-CD/**`.

## Specialization Contract
- `CODE_REVIEW.md` specializes this index-level review contract with deep review
  criteria.
- Review docs must not weaken inherited security/compliance/test constraints.
- If review guidance conflicts with implementation guidance, prefer stricter
  quality/safety interpretation and open a follow-up rule issue for alignment.

## Files
- [CODE_REVIEW.md](CODE_REVIEW.md) - Code review guidance and checklist.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep review criteria and examples in `CODE_REVIEW.md`.
- When adding review sub-docs, update this index and align semantic
  dependencies in `CORE/RULE_DEPENDENCY_TREE.md`.
