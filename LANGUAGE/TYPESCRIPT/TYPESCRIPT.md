# TYPESCRIPT

Guidance for AI agents implementing and reviewing TypeScript code.

## Scope
- Define TypeScript-specific rules that specialize JavaScript guidance.
- Apply this file for all `.ts`/`.tsx` implementation and review tasks.

## Semantic Dependencies
- Inherit JavaScript baseline from
  `LANGUAGE/JAVASCRIPT/JAVASCRIPT.md`.
- Inherit cross-cutting constraints from:
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- Inherit naming/readability expectations from:
  `LANGUAGE/CONVENTIONS.md`, `LANGUAGE/READABILITY.md`.
- Framework docs may further specialize TypeScript usage, but must not weaken
  type-safety and runtime-safety constraints.

## Compiler and Project Defaults
- Enable and keep strict mode enabled (`"strict": true`).
- Treat type errors as blocking for merges in CI.
- Keep `noImplicitOverride`, `noUncheckedIndexedAccess`, and
  `exactOptionalPropertyTypes` enabled unless a documented project constraint
  prevents it.
- Keep `skipLibCheck` disabled by default for long-term correctness;
  enable only with explicit rationale.

## Typing Strategy
- Prefer precise domain types over broad primitives and ad-hoc objects.
- Prefer `type` aliases for unions/utility composition and `interface` for
  extendable object contracts; use whichever is clearer for the case.
- Avoid `any`.
  If unavoidable at a boundary, isolate it and narrow immediately.
- Prefer `unknown` for untrusted inputs and narrow with type guards.
- Model state variants with discriminated unions instead of boolean flags.
- Use `readonly` for immutable APIs and value objects.

## Runtime Boundary Rules
- TypeScript types do not validate runtime data.
- Validate untrusted external data at boundaries (HTTP, queue, env, file).
- Convert validated payloads into internal domain types before deeper logic.
- Do not expose transport-layer DTOs as internal domain models by default.

## Nullability and Optionality
- Keep `null`/`undefined` handling explicit.
- Avoid non-null assertions (`!`) unless a documented invariant exists.
- Prefer control-flow narrowing and guard functions over assertions.
- Use optional properties intentionally; avoid optional fields for required
  lifecycle states.

## API and Module Design
- Keep public API signatures stable and explicit.
- Prefer return types that communicate failure explicitly
  (`Result`-like unions, typed errors) for expected error paths.
- Avoid large "utility" modules mixing unrelated concerns.
- Keep module side effects explicit and minimal.

## Naming Conventions
- Variables, parameters, properties, and functions: `camelCase`.
- Types, interfaces, classes, enums, namespaces: `PascalCase`.
- Enum members: `PascalCase` (TypeScript ecosystem convention).
- `const` values:
  use `camelCase` for local/runtime values;
  use `UPPER_SNAKE_CASE` only for shared true constants.
- Treat abbreviations as one word for casing (`userId`, `httpServer`).

## Enums and Alternatives
- Prefer union literals (`type Status = "Draft" | "Published"`) when values
  are simple and no runtime enum object is required.
- Use enums when runtime reflection/iteration or interop requires them.
- Avoid heterogeneous enums.
- Prefer explicit string values for externally persisted or serialized enums.

## Error Handling
- Throw `Error` subclasses with actionable context.
- Do not throw raw strings or untyped objects.
- Preserve error cause chains when wrapping.
- For async paths, ensure rejected promises are observed and handled.

## Decorators and JSDoc Order
- For decorated classes, place JSDoc immediately above the top-most decorator.
- Keep decorators contiguous and directly above the class declaration.
- Do not place JSDoc between a decorator and the class declaration.
- Use one ordering style consistently across the codebase to avoid formatter and
  tooling ambiguity.

## Performance and Build Hygiene
- Avoid unnecessary type-level complexity that harms compile performance.
- Keep deeply recursive conditional types bounded and documented.
- Avoid broad barrel exports that cause accidental import bloat.
- Prefer `import type` for type-only imports where appropriate.

## High-Risk Pitfalls
1. Using `any` broadly and losing type guarantees.
2. Trusting runtime payload shape from static types alone.
3. Overusing non-null assertions to silence compiler checks.
4. Non-exhaustive union handling with silent default branches.
5. Enum/string mismatch at API boundaries.
6. Type-safe signatures with unsafe internal casts.
7. Leaking framework/transport DTOs into domain core.

## Do / Don't Examples
### 1. `any` vs `unknown` Narrowing
```ts
// Don't: any bypasses type safety.
function parsePayload(payload: any): User {
  return { id: payload.id, name: payload.name };
}

// Do: unknown + explicit narrowing.
function parsePayload(payload: unknown): User {
  if (!isUserPayload(payload)) {
    throw new Error("Invalid user payload");
  }
  return { id: payload.id, name: payload.name };
}
```

### 2. Exhaustive Union Handling
```ts
// Don't: silent default can hide new states.
function renderStatus(status: Status): string {
  switch (status.kind) {
    case "loading": return "Loading";
    case "ready": return "Ready";
    default: return "Unknown";
  }
}

// Do: enforce exhaustiveness.
function renderStatus(status: Status): string {
  switch (status.kind) {
    case "loading": return "Loading";
    case "ready": return "Ready";
  }
  const _exhaustive: never = status;
  return _exhaustive;
}
```

### 3. Enum Naming Convention
```ts
// Don't: Java-style enum member casing.
enum RetryPolicy {
  NO_RETRY,
  EXPONENTIAL_BACKOFF,
}

// Do: TypeScript-standard PascalCase members.
enum RetryPolicy {
  NoRetry,
  ExponentialBackoff,
}
```

### 4. JSDoc and Decorator Order
```ts
// Don't: mixed ordering around decorated class declarations.
@Injectable()
/** Handles invoice orchestration. */
export class InvoiceService {}

// Do: put JSDoc above the top-most decorator.
/** Handles invoice orchestration. */
@Injectable()
export class InvoiceService {}
```

## Code Review Checklist for TypeScript
- Is strict typing preserved without `any` leakage?
- Are untrusted inputs validated at runtime boundaries?
- Are union types handled exhaustively?
- Are null/undefined paths explicit and safe?
- Are casts/assertions minimal and justified?
- Are naming conventions consistent with TypeScript standards?
- Is JSDoc/decorator ordering for decorated classes consistent with this file?
- Are public types cohesive, stable, and domain-focused?
- Are async error paths typed and handled intentionally?

## Testing Guidance for TypeScript
- Test boundary validators and type guards with invalid inputs.
- Test all union variants and exhaustiveness-sensitive branches.
- Add regression tests for null/undefined edge cases.
- Test serialization/deserialization for enum and literal union values.
- Keep type-checking in CI (`tsc --noEmit` or equivalent) as required quality
  gate.

## VCS Ignore Additions
Add these when using TypeScript (if not already covered by baseline ignore
list):
- `*.tsbuildinfo`
- `dist/`, `out/` when build outputs are generated locally

## Override Notes
- This file narrows JavaScript baseline by enforcing static typing discipline.
- Framework docs may add TS framework idioms (for example React props typing),
  but must keep strict boundary validation and safe narrowing rules.
