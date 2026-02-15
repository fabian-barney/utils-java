# AOP

Guidance for AI agents applying Aspect-Oriented Programming responsibly.

## Scope
- Define when AOP is appropriate for cross-cutting concerns.
- Apply this file to aspect design/review across supported frameworks.

## Semantic Dependencies
- Inherit architecture/design boundaries from
  `DESIGN/CLEAN_CODE.md` and `ARCHITECTURE/CLEAN_ARCHITECTURE.md`.
- Inherit security/logging/testing constraints from
  `SECURITY/SECURITY.md`, `CORE/LOGGING.md`, and `TEST/TEST.md`.

## Appropriate AOP Use Cases
- Logging/tracing instrumentation.
- Metrics/timing instrumentation.
- Security checks and policy enforcement wrappers.
- Transaction boundaries.
- Retry/circuit-breaker wrappers.

## Boundaries and Guardrails
- Keep domain business rules out of aspects.
- Keep method responsibilities cohesive ("do one thing") so join points remain
  explicit at method boundaries.
- Keep pointcuts narrow, explicit, and auditable.
- Keep aspect ordering deterministic when multiple aspects apply.
- Keep side effects observable and documented.
- Avoid hidden control-flow changes that surprise maintainers.

## Aspect Design Rules
- Keep advice logic small and focused.
- Keep cross-cutting seams at clear method contracts; avoid embedding multiple
  unrelated concerns inside one large method body.
- Avoid mutating method arguments/results unless explicitly intended.
- Preserve exception semantics unless mapping is deliberate.
- Keep aspect configuration centralized and discoverable.

## High-Risk Pitfalls
1. Business logic hidden in aspects.
2. Broad pointcuts unintentionally capturing unrelated methods.
3. Undocumented aspect ordering conflicts.
4. Aspects swallowing exceptions and hiding failures.
5. Performance overhead from heavy around-advice on hot paths.
6. Mixed-responsibility methods where desired cross-cutting boundary exists
   only inside method internals (not at method contracts).

## Do / Don't Examples
### 1. Cross-Cutting Classification
```text
Don't: implement domain discount rules in aspect advice.
Do:    keep domain rules in services/use cases; use AOP for cross-cutting policy.
```

### 2. Pointcut Scope
```text
Don't: pointcut matching "all public methods" globally.
Do:    target specific package/annotation boundary.
```

### 3. Error Transparency
```text
Don't: catch and ignore exceptions in advice.
Do:    log/annotate and rethrow or map intentionally.
```

### 4. Clean-Code Boundary Readiness
```text
Don't: keep validation, domain mutation, and notification side effects in one
       method, then expect AOP to target only one inner block.
Do:    split responsibilities into focused methods so pointcuts can bind at
       clear method contracts.
```

## Code Review Checklist for AOP
- Is concern truly cross-cutting and reusable?
- Are pointcuts narrow and intentional?
- Is aspect ordering defined and safe?
- Are side effects/exception semantics explicit?
- Is aspect behavior observable through logs/metrics/tests?
- Is base code organized with cohesive methods so pointcuts can target
  contract-level boundaries cleanly?
- Would explicit composition be clearer than AOP for this case?

## Testing Guidance
- Add focused tests for advice trigger boundaries.
- Add integration tests validating composed aspect behavior/order.
- Test exception paths and ensure failures are not masked.
- Measure performance impact on affected hot paths.

## Override Notes
- Framework-specific AOP mechanics may differ, but cross-cutting-only scope and
  transparency constraints here remain mandatory.
