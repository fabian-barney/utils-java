# JAVA

Guidance for AI agents implementing and reviewing Java code.

## Scope
- Define the baseline Java ruleset for correctness, maintainability, and
  interoperability.
- Apply this file for all Java code generation and code review tasks.
- Use `EFFECTIVE_JAVA.md` as an additional specialization layer, not a
  substitute for this baseline.

## Semantic Dependencies
- Inherit cross-cutting constraints from:
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- Inherit shared language constraints from:
  `LANGUAGE/CONVENTIONS.md`, `LANGUAGE/READABILITY.md`.
- `LANGUAGE/JAVA/EFFECTIVE_JAVA.md` narrows and enriches this baseline.
- Framework/library docs (for example Spring, JPA) may specialize API usage
  but must not weaken Java safety constraints.

## Defaults
- Prefer immutability for value types and DTOs.
- Prefer constructor injection and explicit dependencies.
- Keep methods/classes focused and cohesive.
- Prefer interfaces for contracts and dependency boundaries.
- Prefer explicit domain-specific types over primitive obsession.
- Prefer checked validation and fail-fast preconditions at boundaries.

## Nullability and Optional
- Avoid returning `null` from public APIs where absence is expected;
  prefer `Optional<T>` for return values when semantically meaningful.
- Do not use `Optional` for fields, method parameters, or serialization models
  unless there is a strong documented reason.
- Keep null-handling explicit at boundaries and legacy integration points.

## Collections and Mutability
- Return immutable or unmodifiable views where mutation is not intended.
- Make defensive copies for mutable inputs/outputs crossing boundaries.
- Prefer specific collection interfaces in signatures (`List`, `Map`, `Set`).
- Avoid exposing internal mutable collections directly.

## Exception Design
- Throw specific exception types with actionable context.
- Do not swallow exceptions silently.
- Keep exception mapping consistent at API boundaries.
- Preserve root cause when wrapping exceptions.
- Use checked exceptions for genuinely recoverable scenarios; otherwise use
  runtime exceptions with clear domain meaning.

## API and Class Design
- Keep constructors light; avoid side effects and IO in constructors.
- Use builders for objects with many optional parameters.
- Keep equals/hashCode/toString aligned with type semantics.
- Avoid large utility classes with mixed responsibilities.
- Avoid boolean parameter combinations that hide intent; introduce
  explicit value objects or methods.

## Concurrency Baseline
- Avoid shared mutable state by default.
- Prefer immutable handoff between threads.
- When synchronization is required, define and document invariants.
- Use high-level concurrency utilities over manual thread management.
- Keep blocking calls out of latency-critical paths where possible.

## Streams and Functional Style
- Use streams for readable transformations, not as a blanket replacement.
- Avoid side effects inside stream operations.
- Keep stream pipelines understandable; extract named methods when complex.
- Prefer loops when they are clearer than chained stream operations.

## Persistence/Serialization Boundaries
- Keep domain models independent from persistence/transport annotations when
  practical.
- Use dedicated DTOs for external boundaries.
- Avoid leaking persistence entities across API boundaries by default.

## High-Risk Pitfalls
1. Returning internal mutable state directly.
2. Overusing `Optional` in fields/parameters.
3. Catching broad exceptions and hiding root causes.
4. Mixing domain and infrastructure concerns in one class.
5. Blocking operations in shared thread pools without capacity planning.
6. Overly clever stream chains harming readability.
7. Implicit null contracts with no annotations/documentation.

## Do / Don't Examples
### 1. Defensive Copying
```java
// Don't
public List<Item> getItemsUnsafe() {
  return items;
}
```

```java
// Do
public List<Item> getItemsSafe() {
  return List.copyOf(items);
}
```

### 2. Exception Mapping
```java
// Don't: swallow and continue silently.
try {
  repository.save(order);
} catch (Exception ignored) {
}

// Do: keep context and propagate meaningfully.
try {
  repository.save(order);
} catch (DataAccessException ex) {
  throw new OrderPersistenceException(order.id(), ex);
}
```

### 3. Optional Usage
```java
// Don't
private Optional<String> optionalMiddleName;
```

```java
// Do
private String middleName;

public Optional<String> middleName() {
  return Optional.ofNullable(middleName);
}
```

## Code Review Checklist for Java
- Are mutability boundaries explicit and safe?
- Are nullability contracts explicit and consistent?
- Are exceptions specific, contextual, and non-silent?
- Is class/method responsibility cohesive?
- Are public APIs stable and domain-oriented?
- Are concurrency assumptions documented and safe?
- Are stream usages readable and side-effect free?
- Are persistence/transport concerns separated from domain where appropriate?

## Testing Guidance for Java
- Test null/absence behavior for public APIs.
- Test exception mapping and preserved causes.
- Test mutability boundaries (defensive copy and immutability expectations).
- Test concurrency-sensitive code for race and visibility risks.
- Add regression tests for previous bug classes (state leaks, conversion errors,
  mapper issues).

## VCS Ignore Additions
Add these when using Java build tools (if not already covered by baseline
ignore rules):
- `target/`, `build/`
- `*.class`, `*.war`, `*.ear`
- `pom.xml.tag`, `pom.xml.releaseBackup`, `pom.xml.versionsBackup`,
  `pom.xml.next`
- `release.properties`, `dependency-reduced-pom.xml`,
  `buildNumber.properties`
- `.gradle/`

Do not ignore wrapper scripts or wrapper JARs required to build projects
(for example `gradle/wrapper/gradle-wrapper.jar`,
`.mvn/wrapper/maven-wrapper.jar`).

## Companion Specialization
- [EFFECTIVE_JAVA.md](EFFECTIVE_JAVA.md) - deeper effective Java idioms and
  review heuristics.

## Override Notes
- This file is the Java baseline.
- `EFFECTIVE_JAVA.md` and Java framework/library docs may narrow patterns for
  specific contexts, but must keep this file's safety and clarity defaults.
