# TEST

Guidance for AI agents implementing, updating, and reviewing tests.

## Scope
Defines baseline testing expectations for all stacks and domains.

## Semantic Dependencies (Upstream Rules)
- Inherits `CORE/CORE.md` and `CORE/RULE_DEPENDENCY_TREE.md` precedence.
- Security-sensitive scenarios must also satisfy `SECURITY/SECURITY.md`.
- Language/framework/library-specific test techniques may specialize this
  baseline but must not weaken it without explicit rationale.

## Testing Defaults
- Add or update tests for every behavior change.
- Prefer deterministic and isolated tests.
- Keep test suites fast enough for frequent CI execution.
- Use risk-based depth: higher business/security impact requires stronger test
  coverage across layers.

## Test Strategy by Layer
- Unit tests:
  - Default first choice for business logic.
  - Must be fast, isolated, and behavior-focused.
- Integration tests:
  - Required for boundaries (DB, queues, file systems, external services).
  - Validate wiring, contracts, and transactional behavior.
- End-to-end tests:
  - Cover critical user/value flows only.
  - Keep suite small, stable, and non-flaky.

## Determinism and Flakiness Control
- Avoid reliance on wall-clock time, random seeds, network timing, and shared
  mutable state without explicit control.
- Use stable fixtures and explicit setup/teardown.
- Control time/randomness with test doubles where feasible.
- Quarantine and fix flaky tests; do not normalize flaky behavior as acceptable.

## Mocks, Stubs, and Fakes
- Mock boundaries, not core behavior under test.
- Avoid over-mocking that hides integration risks.
- Use fakes/stubs for slow or unavailable dependencies where appropriate.
- Reset shared test doubles between tests to avoid cross-test coupling.

## Data and Fixtures
- Keep fixtures in test-only paths.
- Do not ship test fixtures in production artifacts.
- Prefer minimal fixture data that expresses intent clearly.
- Use scenario-based datasets for edge and failure-path coverage.

## Coverage and Confidence
- High line coverage alone is insufficient; prioritize meaningful assertions and
  branch/error-path validation.
- Cover happy paths, edge cases, failure modes, and regression paths.
- If coverage targets are not met, document exact gaps and risk rationale.

## CI and Reporting Expectations
- Run relevant tests before opening a PR.
- Report what was executed and what was not.
- In CI, fail builds on test failures.
- Publish actionable reports/artifacts for failed runs when available.

## High-Risk Pitfalls
1. Changing behavior without adding/updating tests.
2. Treating snapshot-only assertions as sufficient behavior verification.
3. Accepting flaky tests to keep pipelines green.
4. Mocking internals so heavily that integration defects are hidden.
5. Using shared mutable fixtures that create order-dependent failures.
6. Ignoring error-path and boundary-condition testing.
7. Claiming confidence from coverage metrics without assertion quality.

## Do / Don't Examples
### 1. Behavior Change Coverage
```text
Don't: merge a behavior change without updating tests.
Do:    add/adjust tests for happy path, edge cases, and failure paths.
```

### 2. Flakiness Control
```text
Don't: rerun flaky tests until green and call it stable.
Do:    quarantine/fix flaky tests and remove nondeterministic dependencies.
```

### 3. Assertion Quality
```text
Don't: rely only on snapshots for critical behavior.
Do:    use explicit assertions tied to business-relevant outcomes.
```

## Code Review Checklist for Testing
- Do behavior changes include corresponding tests?
- Are tests deterministic and independent of execution order?
- Are boundary integrations covered where risk demands it?
- Are failure paths and edge cases explicitly validated?
- Are assertions behavior-focused (not only implementation details)?
- Are mocks/stubs used at the right boundary level?
- Are fixture/test assets correctly isolated from production packaging?
- Is test execution scope and result reporting clear in PR notes?

## Validation Notes for Delivery
When delivering a change, include:
- Tests executed (unit/integration/e2e).
- Key manual checks (if applicable).
- Known test gaps and risk justification.
- Follow-up issue references for deferred test debt.

## Override Notes
- Downstream docs may specialize this baseline (for example framework-specific
  testing patterns), but must not reduce determinism, coverage of critical
  paths, or reporting clarity without explicit justification.
