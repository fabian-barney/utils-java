# JUNIT

Guidance for AI agents implementing and reviewing JUnit tests.

## Scope
- Define JUnit-specific testing patterns for reliable, readable tests.
- Apply this file to unit/integration tests using JUnit 5.

## Semantic Dependencies
- Inherit global testing baseline from `TEST/TEST.md`.
- Inherit language/readability baselines from
  `LANGUAGE/JAVA/JAVA.md`, `LANGUAGE/CONVENTIONS.md`, and
  `LANGUAGE/READABILITY.md`.
- `LIBRARY/MOCKITO.md` and other test-library docs may specialize
  mocking/integration behavior.

## Defaults
- Use JUnit 5.
- Keep tests deterministic and isolated.
- Use descriptive test names based on behavior and expectation.
- Keep one primary assertion intent per test.
- Prefer Arrange-Act-Assert structure.

## Test Design Rules
- Test observable behavior, not implementation details.
- Prefer small focused tests over large scenario monoliths.
- Keep setup explicit and local; avoid hidden magic fixtures.
- Avoid shared mutable state across tests.
- Use nested tests only when they improve readability and context.

## Lifecycle and Fixtures
- Default to per-test instance lifecycle unless a clear optimization need exists.
- Use `@BeforeEach`/`@AfterEach` for isolation-safe setup/cleanup.
- Keep expensive shared fixtures explicit and immutable when possible.

## Parameterized and Edge Testing
- Use parameterized tests for input matrix coverage.
- Include boundary, invalid, and edge-case scenarios.
- Keep parameter sources readable and domain-relevant.

## Flakiness Controls
- Avoid real network/time randomness in unit tests.
- Use fake clocks and controlled randomness where needed.
- Avoid order-dependent tests.
- Keep concurrency tests deterministic and bounded.

## High-Risk Pitfalls
1. Shared mutable fixture state causing test order dependence.
2. Overly broad tests with unclear failure intent.
3. Assertions tied to private implementation details.
4. Missing edge-case coverage for boundary conditions.
5. Time/network-based flakiness in unit tests.
6. Test names that do not describe behavior.

## Do / Don't Examples
### 1. Test Naming
```text
Don't: test1(), shouldWork()
Do:    returnsValidationErrorWhenEmailIsInvalid()
```

### 2. Isolation
```text
Don't: static mutable list reused across tests.
Do:    fresh fixture setup in @BeforeEach.
```

### 3. Edge Coverage
```text
Don't: only happy-path assertions.
Do:    include invalid input and boundary cases.
```

## Code Review Checklist for JUnit
- Are tests deterministic and isolated?
- Do test names communicate behavior and condition?
- Is test scope focused with clear assertion intent?
- Are boundary/error paths covered?
- Are fixtures/setup explicit and maintainable?
- Are flakiness risks (time/network/order) addressed?

## Testing Guidance
- Run full test suite with consistent environment settings.
- Track flaky tests and quarantine/remediate quickly.
- Add coverage for bug-fix regressions.
- Keep CI reports readable with failing-test diagnostics.

## Override Notes
- Library/framework test docs (Mockito, Spring test slices, etc.) may add
  specialization, but JUnit determinism and clarity constraints remain
  mandatory.
