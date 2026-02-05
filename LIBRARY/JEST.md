# JEST

Guidance for Jest testing.

## General
- Keep tests deterministic; avoid real network and time.
- Use descriptive `describe`/`test` names that capture behavior.
- Reset shared state in `beforeEach`/`afterEach` to avoid cross-test coupling.

## Mocks
- Mock boundaries, not the unit under test.
- Clear or restore mocks between tests (`clearMocks`/`restoreMocks` or explicit calls).

## Timers
- Use fake timers only when needed and always restore real timers after.

## Snapshots
- Prefer explicit assertions for small objects.
- Use snapshots for large or UI-driven outputs; review diffs carefully.
