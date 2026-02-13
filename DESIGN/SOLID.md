# SOLID

Guidance for AI agents applying SOLID principles pragmatically.

## Scope
- Define practical SOLID usage for maintainable, extensible software design.
- Apply this file during design and review, not as rigid dogma.

## Semantic Dependencies
- Inherit cross-cutting precedence/override model from
  `CORE/RULE_DEPENDENCY_TREE.md`.
- `DESIGN/CLEAN_CODE.md` and `ARCHITECTURE/CLEAN_ARCHITECTURE.md` are related
  companion docs that apply alongside this file.

## SRP: Single Responsibility Principle
- Keep modules with one primary reason to change.
- Separate orchestration, policy, and IO responsibilities.
- Avoid classes that mix domain logic, persistence, and transport concerns.

## OCP: Open/Closed Principle
- Design extension seams where change vectors are expected.
- Prefer composition/polymorphism over branching explosion for variants.
- Avoid speculative abstractions without credible extension need.

## LSP: Liskov Substitution Principle
- Subtypes must honor base type contracts.
- Do not strengthen preconditions or weaken postconditions.
- Keep behavioral compatibility explicit in inheritance hierarchies.

## ISP: Interface Segregation Principle
- Prefer narrow interfaces focused on client needs.
- Avoid large kitchen-sink interfaces.
- Keep interface methods cohesive and role-specific.

## DIP: Dependency Inversion Principle
- Depend on abstractions across boundary seams.
- Keep high-level policy independent from low-level framework details.
- Compose implementations at outer boundaries.

## Tradeoff Guidance
- SOLID is a decision aid, not a hard quota.
- Prefer simple direct code when extension pressure is low.
- Introduce abstraction when it reduces expected change cost.
- Document deliberate deviations when pragmatic constraints apply.

## High-Risk Pitfalls
1. Over-abstraction from dogmatic SOLID application.
2. Huge interface hierarchies with little value.
3. Inheritance hierarchies violating substitutability.
4. Dependency inversion applied superficially while leaking concrete types.
5. SRP ignored in service/controller "god objects".

## Do / Don't Examples
### 1. SRP
```text
Don't: one class validates input, persists data, and sends notifications.
Do:    separate responsibilities into focused collaborators.
```

### 2. OCP
```text
Don't: giant if/else for every new payment type.
Do:    strategy interface with pluggable implementations.
```

### 3. ISP
```text
Don't: Repository interface with unrelated read/write/admin methods.
Do:    split read and write interfaces by client need.
```

## Code Review Checklist for SOLID
- Does each module have a clear reason to change?
- Are extension points aligned with actual change vectors?
- Are subtype contracts behaviorally compatible?
- Are interfaces client-focused and minimal?
- Are high-level modules isolated from low-level details?
- Is abstraction level proportional to complexity?

## Testing Guidance
- Add contract tests for polymorphic interfaces/subtypes.
- Add focused unit tests for strategy implementations.
- Add architecture tests to enforce dependency direction.
- Add regression tests when refactoring responsibilities/interfaces.

## Override Notes
- Framework constraints may require pragmatic compromises, but contract
  compatibility and boundary isolation principles remain mandatory.
