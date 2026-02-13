# JEST

Guidance for AI agents implementing and reviewing Jest tests.

## Scope
- Define Jest-specific rules for deterministic, maintainable tests.
- Apply this file to unit/integration tests executed with Jest.

## Semantic Dependencies
- Inherit baseline testing constraints from `TEST/TEST.md`.
- Inherit JavaScript/TypeScript language constraints from `LANGUAGE/**` docs.
- Playwright/Selenium docs may cover complementary browser E2E testing.

## Defaults
- Keep tests deterministic and isolated.
- Use descriptive `describe`/`it` names reflecting behavior.
- Reset shared state between tests.
- Prefer explicit assertions over brittle snapshots when scope is small.
- Keep async tests explicit (`await`, `resolves`, `rejects`).

## Mocking and Module Isolation
- Mock boundaries (network, filesystem, external services), not core logic.
- Keep mock setup local to scenario where possible.
- Reset/restore mocks between tests.
- Avoid deep global mocks that hide behavior changes.

## Async and Timer Rules
- Always await async operations.
- Avoid dangling promises and unhandled rejections.
- Use fake timers only when needed and restore real timers after test.
- Keep timer-based tests explicit about scheduled operations.

## Snapshot Discipline
- Use snapshots for large stable outputs where explicit assertions are noisy.
- Keep snapshots focused and reviewed like code.
- Avoid broad snapshot capture that hides meaningful regression signals.

## Flakiness Controls
- Avoid real network/time randomness in unit tests.
- Control random inputs with deterministic seeds.
- Keep test order independence.
- Quarantine/remediate flaky tests quickly.

## High-Risk Pitfalls
1. Snapshot overuse masking semantic regressions.
2. Shared global mocks leaking between tests.
3. Fake timers not restored, impacting later tests.
4. Async tests passing falsely due to missing await/return.
5. Over-mocking implementation details.
6. Real IO in unit tests causing instability.

## Do / Don't Examples
### 1. Async Await
```ts
// Don't
test("saves user", () => {
  saveUser(input);
  expect(repo.save).toHaveBeenCalled();
});

// Do
test("saves user", async () => {
  await saveUser(input);
  expect(repo.save).toHaveBeenCalled();
});
```

### 2. Snapshot Scope
```text
Don't: snapshot entire huge page object for tiny behavior change.
Do:    assert explicit critical fields or focused snapshot fragment.
```

### 3. Timer Cleanup
```text
Don't: jest.useFakeTimers() without restoring.
Do:    use fake timers in test scope and restore afterwards.
```

## Code Review Checklist for Jest
- Are tests deterministic and isolated?
- Are async paths awaited and assertion-complete?
- Are mocks boundary-focused and reset between tests?
- Are snapshots focused and intentionally reviewed?
- Are fake timers and globals cleaned up safely?
- Are edge/error cases represented?

## Testing Guidance
- Run tests with parallel and serial modes for flaky detection where needed.
- Track flaky tests and stabilize quickly.
- Add regression tests for bug-fix scenarios.
- Keep CI reporting for failed test diagnostics and timing.

## Override Notes
- Framework-specific testing utilities may extend Jest usage, but deterministic
  async handling and isolation constraints here remain mandatory.
