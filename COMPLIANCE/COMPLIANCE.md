# COMPLIANCE

Compliance-layer contract for legal, licensing, and governance constraints that
must be preserved across all technology choices.

## Role in the Ruleset
- COMPLIANCE defines non-negotiable legal/governance constraints for dependency
  selection and delivery artifacts.
- Compliance constraints are part of the cross-cutting baseline
  (currently anchored by `COMPLIANCE/LICENSES.md`) and inherited by all
  downstream language/framework/library/build/infrastructure/CI/CD docs.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
COMPLIANCE includes:
- License compatibility constraints.
- Attribution and third-party notice obligations.
- Governance expectations that affect technology selection and distribution.

COMPLIANCE does not include:
- API-level coding guidance.
- Framework runtime behavior.
- Build/pipeline implementation details, except where needed for compliance
  enforcement.

Those belong in `LANGUAGE/**`, `FRAMEWORK/**`, `LIBRARY/**`,
`BUILD_TOOLS/**`, `INFRASTRUCTURE/**`, and `CI-CD/**`.

## Specialization Contract
- Child compliance docs may narrow compliance constraints for specific legal
  domains (for example, license policy).
- Downstream docs must not weaken compliance constraints without explicit,
  reviewed governance approval.
- If a technical recommendation conflicts with compliance rules, compliance is
  authoritative and the technical guidance must be revised.

## Files
- [LICENSES.md](LICENSES.md) - License compliance and allowed licenses.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep legal/policy specifics in child compliance docs.
- When adding compliance docs, update this index and align semantic dependencies
  in `CORE/RULE_DEPENDENCY_TREE.md`.
