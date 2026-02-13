# GOF_DESIGN_PATTERNS

Guidance for AI agents applying GoF patterns pragmatically.

## Scope
- Define when GoF patterns are useful and when they are overengineering.
- Apply this file for architecture/design decisions and code review.

## Semantic Dependencies
- Inherit SOLID and clean-code constraints from `DESIGN/SOLID.md` and
  `DESIGN/CLEAN_CODE.md`.
- Inherit architecture boundary constraints from `ARCHITECTURE/**`.

## Pattern Selection Principles
- Use patterns to reduce concrete change cost for known change vectors.
- Do not introduce patterns solely for textbook conformity.
- Keep pattern intent explicit in naming/docs when it aids maintainability.
- Prefer simpler direct code when variation pressure is low.

## Creational Pattern Guidance
- Factory Method / Abstract Factory:
  use when object family/instantiation varies by environment/context.
- Builder:
  use for complex object construction with optional parameters/invariants.
- Singleton:
  avoid as global state; prefer DI-managed lifecycle.

## Structural Pattern Guidance
- Adapter:
  isolate incompatible interfaces.
- Facade:
  simplify interaction with a complex subsystem.
- Decorator:
  extend behavior without subclass explosion.
- Proxy:
  control access, lazy loading, remoting, or policy boundaries.

## Behavioral Pattern Guidance
- Strategy:
  replace branch-heavy algorithm selection.
- Observer:
  decouple event publishers and subscribers.
- Command:
  encapsulate actions for queueing, undo, orchestration.
- State:
  replace state-driven condition pyramids.

## Anti-Pattern Guardrails
- Avoid speculative pattern layering without real variability pressure.
- Avoid pattern names as substitutes for clear domain names.
- Avoid hidden complexity behind facade/proxy without observability.
- Avoid singleton-as-global-variable design.

## High-Risk Pitfalls
1. Pattern inflation for simple code paths.
2. Abstract factories with only one implementation forever.
3. Strategy/state abstractions that obscure trivial logic.
4. Observer chains with implicit side effects and poor tracing.
5. Decorator stacks with unclear execution order.

## Do / Don't Examples
### 1. Variation Pressure
```text
Don't: introduce Strategy with one static algorithm and no expected variation.
Do:    keep direct implementation until variation emerges.
```

### 2. Singleton Misuse
```text
Don't: use Singleton for mutable global cache state.
Do:    inject scoped cache service via DI container.
```

### 3. Adapter Boundary
```text
Don't: scatter external API shape conversions across business logic.
Do:    isolate conversion in dedicated Adapter.
```

## Code Review Checklist for GoF Pattern Use
- Is there a clear change vector justifying this pattern?
- Does pattern use reduce complexity or increase it?
- Are responsibilities and boundaries clearer after applying the pattern?
- Are side effects and execution order observable/testable?
- Are simpler alternatives inferior for this context?

## Testing Guidance
- Add contract tests for pattern interfaces/strategies.
- Add behavioral tests for state transitions and decorator/proxy composition.
- Add integration tests around adapter/facade boundary assumptions.
- Add regression tests when refactoring branch logic into patterns.

## Override Notes
- Framework conventions may impose structural patterns implicitly, but pattern
  adoption should remain intent-driven and complexity-aware.
