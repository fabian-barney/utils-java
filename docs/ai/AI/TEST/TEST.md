# TEST

Testing expectations and verification notes.

## Expectations
- Add tests when behavior changes.
- Keep tests deterministic and fast.
- Document when tests were not run.

## Test Types
- Unit tests: fast, isolated, focused on logic in a single unit.
- Integration tests: verify boundaries (DB, messaging, external services) and
  wiring between components.
- E2E tests: cover critical user journeys only; keep the suite small and stable.

## Mocks and Edge Cases
- Mocks are acceptable and useful for modeling edge cases around the unit under
  test.
- Use mocks to simulate failures, timeouts, and rare conditions that are hard to
  reproduce in real integrations.
- Avoid over-mocking in ways that hide real integration risks.

## Test Strategy
- Default to unit tests for business logic.
- Require integration tests only for boundary behavior and wiring.
- Keep E2E tests limited to critical user journeys.
