# PLAYWRIGHT

Guidance for AI agents implementing and reviewing Playwright tests.

## Scope
- Define Playwright E2E/integration testing rules for reliability and
  maintainability.
- Apply this file to browser automation tests and test infrastructure.

## Semantic Dependencies
- Inherit testing baseline from `TEST/TEST.md`.
- Inherit UI accessibility semantics from `LANGUAGE/HTML/HTML.md`.
- Complements unit/component testing docs (Jest/JUnit etc.).

## Defaults
- Focus E2E tests on critical user journeys.
- Keep tests deterministic with isolated test data/state.
- Use robust locators (`getByRole`, test IDs via `data-testid` +
  `getByTestId`) over brittle CSS/XPath chains.
- Use Playwright auto-waiting and explicit assertions.
- Keep setup/teardown reusable through fixtures.

## Locator and Interaction Rules
- Prefer accessible-role/text/test-id locators.
- Avoid selectors tied to fragile DOM structure.
- Keep waits condition-based, not sleep-based.
- Keep user actions explicit and semantically aligned.

## Flakiness Controls
- Avoid real third-party dependencies when stubbing is possible.
- Isolate each test scenario state.
- Use retries sparingly; fix root cause first.
- Keep parallelization aware of shared-resource contention.

## Debuggability and Artifacts
- Capture traces/screenshots/videos on failure according to policy.
- Keep logs/artifacts easy to correlate with failed scenario.
- Use step-level diagnostics for complex flows.

## High-Risk Pitfalls
1. Brittle selectors coupled to markup churn.
2. Arbitrary sleeps causing flaky and slow tests.
3. Shared state leakage between tests.
4. Overbroad E2E coverage duplicating lower-level tests.
5. Ignoring artifact capture, slowing triage.
6. Uncontrolled retries hiding real instability.

## Do / Don't Examples
### 1. Locator Strategy
```text
Don't: page.locator("div:nth-child(4) > span").click()
Do:    page.getByRole("button", { name: "Submit" }).click()
```

### 2. Waiting
```text
Don't: await page.waitForTimeout(5000)
Do:    await expect(page.getByText("Saved")).toBeVisible()
```

### 3. Test Isolation
```text
Don't: rely on leftover account/session from previous test.
Do:    create/seed needed state per test fixture.
```

## Code Review Checklist for Playwright
- Are scenarios focused on critical journeys?
- Are locators stable and accessibility-aligned?
- Are waits assertion/event-based (no arbitrary sleeps)?
- Is test state isolated and parallel-safe?
- Are failure artifacts and diagnostics configured?
- Is retry usage justified and bounded?

## Testing Guidance
- Run tests in CI with representative browser matrix as required.
- Track flaky tests and failure categories.
- Add visual/interaction regression coverage only where high value.
- Keep test runtime budget monitored and optimized.

## Override Notes
- Project-specific E2E policy may define stricter locator/test-ID conventions,
  but determinism and stable-locator constraints here remain mandatory.
