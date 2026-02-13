# JAVASCRIPT

Guidance for AI agents implementing and reviewing JavaScript code.

## Scope
- Define a complete JavaScript baseline for runtime-safe, maintainable code.
- Apply this file for plain JavaScript projects and as parent guidance for
  TypeScript and JavaScript-based framework docs.

## Semantic Dependencies
- Inherit cross-cutting constraints from:
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- Inherit shared language constraints from:
  `LANGUAGE/CONVENTIONS.md`, `LANGUAGE/READABILITY.md`.
- Specialized docs (`LANGUAGE/TYPESCRIPT/TYPESCRIPT.md`, framework docs) may
  narrow this guidance but must not silently weaken it.

## Defaults
- Prefer ESM modules (`import`/`export`) over CommonJS for new code unless the
  runtime/toolchain explicitly requires CommonJS.
- Keep modules focused; avoid files that mix unrelated responsibilities.
- Use `const` by default, `let` only when reassignment is required, avoid `var`.
- Prefer explicit `===`/`!==` over loose equality.
- Prefer nullish and optional operators (`??`, `?.`) over deep guard pyramids.
- Prefer pure functions and explicit inputs/outputs for business logic.

## Runtime Boundaries and Validation
- Treat all external data as untrusted:
  HTTP payloads, message queues, environment variables, filesystem input.
- Validate boundary data explicitly before domain use.
- Normalize data once at boundary adapters; keep core logic on normalized shapes.
- Never assume dynamic payload shape from naming conventions or comments.

## Async and Concurrency Rules
- Always handle promise rejections.
- Do not fire-and-forget async work unless intentional and observably tracked.
- Use `Promise.all` only when all tasks may fail-fast together.
- Use `Promise.allSettled` when partial success is acceptable.
- Set explicit timeouts/cancellation where IO can hang.
- Keep async flow linear and readable; avoid nested `.then` chains in new code.

## Error Handling
- Throw `Error` (or subclasses), not raw strings/objects.
- Add contextual metadata when rethrowing (`cause`, operation identifiers).
- Distinguish expected domain outcomes from exceptional failures.
- Do not swallow errors silently; map, log, or rethrow intentionally.

## State and Mutation
- Avoid shared mutable state across modules.
- Prefer immutable updates (`{ ...obj }`, `map`, `filter`) for predictable flow.
- Clone cautiously at boundaries; avoid unnecessary deep cloning in hot paths.
- Keep side effects at edges (IO adapters, framework handlers), not in pure
  domain helpers.

## Control Flow and Readability
- Prefer guard clauses and early returns to reduce nesting.
- Keep functions focused on one responsibility.
- Avoid cascading ternary expressions.
- Keep boolean conditions explicit; extract named predicates when complex.

## Performance Baseline
- Do not optimize blindly; measure first.
- Avoid repeated expensive work inside loops when values can be precomputed.
- Avoid accidental quadratic behavior in list operations on large data.
- Be careful with synchronous CPU-heavy work on event-loop-critical paths.

## Security Baseline
- Never build code paths that evaluate untrusted strings (`eval`, dynamic
  function constructors) unless there is no alternative and controls are strict.
- Avoid unsafe shell command construction from untrusted input.
- Sanitize/encode output for the target context (HTML, URL, shell).
- For SQL, use parameterized queries/prepared statements rather than string
  escaping.

## High-Risk Pitfalls
1. Unhandled promise rejections causing hidden failures.
2. Trusting external payload shape without validation.
3. Mutating shared objects across module boundaries.
4. Using `==`/`!=` and relying on coercion side effects.
5. Silent catch blocks that drop operational failures.
6. Fire-and-forget async tasks without cancellation/logging.
7. Mixing CommonJS and ESM inconsistently in one package.

## Do / Don't Examples
### 1. Promise Rejection Handling
```js
// Don't: unhandled rejection.
function saveUser(user) {
  db.write(user);
}

// Do: await and handle failure with context.
async function saveUser(user) {
  try {
    await db.write(user);
  } catch (error) {
    logger.error("user.save.failed", { userId: user.id, error });
    const wrappedError = new Error(`Failed to save user ${user.id}`, {
      cause: error,
    });
    if (wrappedError.cause === undefined) {
      wrappedError.cause = error;
    }
    throw wrappedError;
  }
}
```

### 2. Boundary Validation
```js
// Don't: trust request body shape.
function createInvoice(req) {
  return invoiceService.create(req.body.amount, req.body.currency);
}

// Do: validate and normalize at boundary.
function createInvoice(req) {
  const payload = validateInvoiceRequest(req.body);
  return invoiceService.create(payload.amount, payload.currency);
}
```

### 3. Shared Mutation
```js
// Don't: mutate imported/shared state.
settings.timeoutMs = 5000;

// Do: create explicit derived config.
const runtimeSettings = { ...settings, timeoutMs: 5000 };
```

## Code Review Checklist for JavaScript
- Are all external inputs validated at boundaries?
- Are promise rejections handled for all async operations?
- Is error handling explicit, contextual, and non-silent?
- Is module state mutation controlled and intentional?
- Are `===`/`!==` used consistently?
- Are side effects isolated from pure domain logic?
- Are there event-loop blocking operations on hot paths?
- Are security-sensitive APIs (`eval`, shell exec, dynamic code paths) avoided
  or tightly controlled?

## Testing Guidance for JavaScript
- Test boundary validators with valid, invalid, and malicious payloads.
- Test async failure paths, including timeout/cancellation behavior.
- Test concurrency behavior for multi-promise flows.
- Test mutation-sensitive logic for unintended shared-state side effects.
- Add regression tests for previously observed runtime edge cases.

## Override Notes
- `LANGUAGE/TYPESCRIPT/TYPESCRIPT.md` narrows typing discipline and compile-time
  safety rules for TypeScript codebases.
- Framework docs may further specialize state/lifecycle/rendering behavior but
  must keep this file's runtime safety baseline.
