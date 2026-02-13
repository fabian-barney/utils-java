# LOMBOK

Guidance for AI agents implementing and reviewing Lombok usage.

## Scope
- Define when Lombok improves clarity and when explicit code is safer.
- Apply this file to Java classes using Lombok annotations.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md` and
  `LANGUAGE/JAVA/EFFECTIVE_JAVA.md`.
- Inherit design/readability constraints from `DESIGN/CLEAN_CODE.md`.

## Defaults
- Use Lombok to remove low-value boilerplate while preserving clarity.
- Prefer `@RequiredArgsConstructor` for dependency injection.
- Prefer `@Slf4j` for logger wiring.
- Prefer `@Builder` for complex immutable object construction.
- Prefer explicit annotations over broad convenience annotations when behavior
  matters.

## Annotation Selection Policy
- `@Getter`/`@Setter`: use selectively based on encapsulation intent.
- `@Value` (`lombok.Value`): prefer for immutable DTO/value objects.
- `@Data`: avoid by default on domain entities and types with nuanced identity.
- `@EqualsAndHashCode`: configure intentionally for inheritance/identity.
- `@ToString`: avoid exposing large graphs or sensitive fields.

## Risky Annotations and Guardrails
- `@SneakyThrows`: use only with explicit rationale and bounded scope.
- `@Builder.Default`: verify semantics for null/optional behavior.
- `@SuperBuilder`: use cautiously; avoid deep inheritance complexity.
- Keep generated behavior understandable to reviewers.

## Tooling and Build Consistency
- Ensure annotation processing is enabled in build and IDE.
- Keep `lombok.config` committed and consistent.
- Configure `lombok.copyableAnnotations` for framework-required constructor
  annotations where needed.
- Avoid IDE/build mismatch where generated code differs.

## High-Risk Pitfalls
1. `@Data` on entities with incorrect equals/hashCode semantics.
2. Hidden behavior from broad annotations reducing readability.
3. `@ToString` leaking sensitive or massive object graphs.
4. `@SneakyThrows` masking API exception contracts.
5. Annotation processing misconfiguration causing compile/runtime drift.

## Do / Don't Examples
### 1. Entity Identity
```text
Don't: @Data on JPA entity with mutable identity semantics.
Do:    explicit getters/setters + deliberate equals/hashCode strategy.
```

### 2. Exception Transparency
```text
Don't: use @SneakyThrows to bypass checked exception design.
Do:    model exception flow explicitly unless justified otherwise.
```

### 3. Constructor Injection
```text
Don't: field injection with mutable dependencies.
Do:    @RequiredArgsConstructor + final dependencies.
```

## Code Review Checklist for Lombok
- Is Lombok reducing boilerplate without hiding critical behavior?
- Are risky annotations (`@Data`, `@SneakyThrows`) justified?
- Are equality/toString semantics safe and intentional?
- Is sensitive data excluded from generated toString output?
- Is lombok config/tooling consistency ensured?
- Are generated constructors aligned with DI framework needs?

## Testing Guidance
- Test equality/hashCode behavior for classes using generated methods.
- Test serialization/mapping behavior for Lombok-built DTOs.
- Validate build + IDE annotation processing consistency in CI.
- Add regression tests when Lombok annotation strategy changes.

## Override Notes
- If project policy prefers explicit boilerplate in critical modules, follow
  stricter module policy. Baseline rule: favor clarity over annotation density.
