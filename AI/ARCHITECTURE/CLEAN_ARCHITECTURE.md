# CLEAN_ARCHITECTURE

Concise rules inspired by Clean Architecture. This is a practical checklist for
system structure and dependency management.

## Principles
- Dependencies point inward (toward domain and use cases).
- Business rules must not depend on frameworks, databases, or UI.
- Keep interfaces at boundaries and depend on abstractions.

## Layers (typical)
- Domain (entities, core rules)
- Application (use cases, orchestration)
- Infrastructure (DB, messaging, external APIs)
- Interface (controllers, UI, delivery mechanisms)

## Guidance
- Keep domain models free of framework annotations where possible.
- Use ports/adapters to isolate external systems.
- Treat frameworks as replaceable details.
- Do not allow infrastructure types to leak into domain logic.
- Keep use cases small and testable.

## Testing
- Prefer fast, isolated tests at inner layers.
- Use integration tests at boundaries and adapters.
