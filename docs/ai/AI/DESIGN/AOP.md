# AOP

Promote the use of Aspect-Oriented Programming (AOP) for cross-cutting concerns.
AOP is appropriate when behavior must be applied consistently across many
components without tangling core business logic.

## Good Fits
- Logging and tracing
- Metrics and performance timing
- Security checks and authorization gates
- Transaction boundaries
- Retry policies and circuit breakers
- Input validation and sanitization (where centralized policies apply)

## Guidance
- Keep core business logic free of cross-cutting concerns.
- AOP reduces tangling and scattering by centralizing cross-cutting concerns
  into advices, avoiding duplication that can diverge over time.
- Use AOP to enforce consistent policies across modules.
- Document each aspect's scope and intent.
- Prefer narrow pointcuts to avoid unexpected behavior.
- Test aspects separately and with integration tests where they apply.

## Cautions
- Avoid hiding significant business logic inside aspects.
- Do not misclassify domain logic as cross-cutting; keep business rules in core code.
- Be careful with ordering when multiple aspects apply.
