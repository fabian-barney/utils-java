# CLEAN_ARCHITECTURE

Guidance for AI agents implementing and reviewing Clean Architecture patterns.

## Scope
- Define dependency-direction and boundary rules for layered architecture.
- Apply this file when designing modules/services and reviewing architectural
  changes.

## Semantic Dependencies
- Inherit core constraints from `ARCHITECTURE/ARCHITECTURE.md`.
- Inherit design principles from `DESIGN/SOLID.md` and
  `DESIGN/CLEAN_CODE.md`.
- Inherit security/testing/logging baselines from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.

## Core Rules
- Dependencies point inward toward domain policy.
- Domain and use-case layers must not depend on frameworks, DB clients, or web
  adapters.
- Outer layers implement interfaces defined by inner layers.
- Keep business policy independent from delivery and persistence details.

## Layer Responsibilities
- Domain:
  entities, value objects, invariants, and core policy.
- Application/use-case:
  orchestration, transaction boundaries, policy composition.
- Interface adapters:
  controllers, presenters, gateways, mappers.
- Infrastructure:
  DB, message bus, network clients, framework integration.

## Boundary Contracts
- Define ports/interfaces at policy boundaries.
- Keep boundary DTOs stable and explicit.
- Avoid leaking framework-specific annotations/types into domain core.
- Keep mapping between boundary DTOs and domain models explicit.

## Dependency Injection and Composition
- Compose concrete dependencies at outermost composition root.
- Inject abstractions into use cases.
- Avoid service locators and hidden global singletons in core policy.

## Transaction and Side-Effect Placement
- Keep side effects in outer layers/gateways.
- Keep use-case logic deterministic where possible.
- Keep transaction scope aligned with use-case boundary.
- Avoid domain-layer calls directly to infrastructure.

## High-Risk Pitfalls
1. Framework annotations leaking into domain models.
2. Use-case logic tightly coupled to ORM/HTTP types.
3. Anemic domain with all business rules pushed to controllers.
4. Shared "utils" bypassing boundaries and dependency direction.
5. Direct infrastructure calls from domain entities.
6. Over-abstraction without clear boundary value.

## Do / Don't Examples
### 1. Dependency Direction
```text
Don't: DomainService imports JdbcTemplate/ORM repository implementation.
Do:    DomainService depends on OrderRepository interface defined in core.
```

### 2. DTO Leakage
```text
Don't: Pass HTTP request objects into use-case methods.
Do:    Map request to explicit input DTO/value object first.
```

### 3. Composition Root
```text
Don't: new InfrastructureClient() inside use-case class.
Do:    inject interface implementation via outer-layer wiring.
```

## Code Review Checklist for Clean Architecture
- Are dependency directions inward and enforced?
- Are framework/infrastructure details isolated to outer layers?
- Are boundary contracts explicit and stable?
- Is business logic located in domain/use-case layers?
- Are mappers/adapters explicit and testable?
- Are transactions and side effects scoped to use-case boundaries?
- Are architecture constraints guarded by tests/static checks?

## Testing Guidance
- Add unit tests for domain invariants and use-case rules.
- Add integration tests for adapter/gateway behavior.
- Add architectural dependency tests (package/module boundary checks).
- Add regression tests when moving code across layers.

## Override Notes
- Framework docs may prescribe wiring patterns, but dependency direction and
  boundary-isolation rules in this file remain mandatory.
