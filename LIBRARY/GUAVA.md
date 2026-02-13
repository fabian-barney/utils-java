# GUAVA

Guidance for AI agents implementing and reviewing Guava usage.

## Scope
- Define when to use Guava utilities versus JDK/native alternatives.
- Apply this file to Java utility, collections, and helper API decisions.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md`.
- Inherit design/readability constraints from `DESIGN/CLEAN_CODE.md` and
  `LANGUAGE/READABILITY.md`.

## Defaults
- Prefer JDK standard library when equivalent functionality exists.
- Use Guava intentionally where it adds clear value.
- Prefer immutable collections (`ImmutableList`, `ImmutableMap`) for shared or
  exposed state.
- Keep Guava usage consistent; avoid mixed utility ecosystems without reason.

## API Usage Rules
- Use `Preconditions` for argument/state validation where clarity improves.
- Prefer `java.util.Optional` over Guava Optional in modern code.
- Use cache utilities cautiously with explicit size/expiry policy.
- Avoid hidden performance costs from repeated immutable-copy churn.

## Dependency and Migration Guardrails
- Avoid introducing Guava solely for trivial helpers.
- Minimize hard coupling to rarely-used Guava APIs when JDK alternatives are
  viable.
- Keep migration path in mind for future Java baseline upgrades.

## High-Risk Pitfalls
1. Adding Guava for features available in current JDK.
2. Mixing Guava Optional with JDK Optional.
3. Unbounded Guava caches causing memory pressure.
4. Excessive immutable copy creation in hot paths.
5. Utility sprawl reducing readability.

## Do / Don't Examples
### 1. Optional Type
```text
Don't: com.google.common.base.Optional in new code.
Do:    java.util.Optional for modern Java APIs.
```

### 2. Cache Policy
```text
Don't: cache without size/expiry constraints.
Do:    explicit maximumSize and expiration policies.
```

### 3. Library Choice
```text
Don't: add Guava only for a simple string join utility.
Do:    use JDK `String.join` when sufficient.
```

## Code Review Checklist for Guava
- Is Guava usage justified over JDK alternatives?
- Are immutable collections used where mutability must be controlled?
- Are caches bounded and policy-driven?
- Is Optional usage modern and consistent?
- Does Guava usage improve clarity rather than add dependency noise?

## Testing Guidance
- Test cache policy behavior (eviction/expiration) where used.
- Test immutability assumptions in exposed collection APIs.
- Add regression tests around utility behavior with edge-case inputs.

## Override Notes
- Project-specific utility standards may restrict Guava usage further.
- Baseline rule: prefer standard library first, Guava second with clear value.
