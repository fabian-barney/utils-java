# GUAVA

Guidance for Guava usage.

## Defaults
- Use Guava where the JDK lacks an equivalent, but prefer standard library types
  when they are sufficient.
- Avoid mixing multiple utility libraries with overlapping functionality.

## Collections and Utilities
- Prefer immutable collections when possible (`ImmutableList`, `ImmutableMap`).
- Use preconditions (`Preconditions.check*`) for argument validation where it
  improves clarity.
- Avoid overuse of `Optional` from Guava in modern Java; prefer `java.util.Optional`.

## Performance
- Be mindful of collections copying; prefer builders for large collections.

## Testing
- Ensure usage is covered by unit tests when Guava utilities are central to logic.
