# EARLY_RETURN

Guidance for AI agents to use early return and guard clauses effectively.

## Scope
- Define when early return is the preferred control-flow style.
- Apply during implementation, refactoring, and review across languages.
- Use this file with readability and clean-code guidance, not as isolated style
  dogma.

## Semantic Dependencies
- Inherit baseline precedence rules from `CORE/RULE_DEPENDENCY_TREE.md`.
- Inherit naming/readability constraints from `LANGUAGE/READABILITY.md`.
- Inherit function cohesion constraints from `DESIGN/CLEAN_CODE.md` and
  `DESIGN/SOLID.md`.

## Defaults and Guardrails
- Prefer early return/guard clauses to keep the happy path linear.
- Validate inputs and preconditions early; on invalid or error states, return
  (or exit) immediately.
- Reduce nested branching depth before extracting deeper abstractions.
- Keep guard clauses small and intent-revealing.
- Keep return points semantically obvious; do not scatter unrelated exits.

Use early return carefully when control-flow exits could bypass critical
cleanup/consistency behavior:
- resource lifecycle obligations,
- transactional boundary guarantees,
- required audit/logging side effects.

Modern language/runtime features (for example structured cleanup constructs,
GC-managed memory, and scoped APIs) reduce these risks in many cases. Treat
early return as the default, and treat caveats as exceptions to check.

## High-Risk Pitfalls
1. Deeply nested condition trees when guard clauses would make intent obvious.
2. Early exits with vague predicates that hide why execution stops.
3. Multiple exits that duplicate side effects in inconsistent ways.
4. Adding guard clauses without tests for changed control-flow paths.
5. Avoiding early return dogmatically and keeping unnecessary nesting.

## Do / Don't Examples
### 1. Guard Clauses over Nested Blocks
```text
Don't:
// Happy path is buried inside multiple nested checks.
if (isValid(request)) {
  if (hasActiveSession(user)) {
    if (!isTokenExpired(token)) {
      process(request);
    }
  }
}

Do:
// Fail fast on invalid or unauthorized states; keep happy path linear.
if (!isValid(request)) return;
if (!hasActiveSession(user)) return;
if (isTokenExpired(token)) return;
process(request);
```

### 2. Keep Shared Side Effects Explicit
```text
Don't: duplicate audit/log side effects in each return branch.
Do:    keep mandatory side effects in one explicit place before return,
       or enforce them with structured constructs.
```

### 3. Predicate Clarity
```text
Don't: if (x) return;
Do:    if (isRateLimitExceeded(user)) return;
```

## Code Review Checklist for Early Return
- Does early return reduce nesting and improve linear readability?
- Are guard predicates explicit and semantically named?
- Are mandatory cleanup/transaction/audit effects still guaranteed?
- Is the happy path easier to understand in one pass?
- Were tests updated for changed control-flow branches?

## Testing Guidance
- Add tests for each guard-clause exit path introduced or changed.
- Keep at least one explicit happy-path test per refactored method.
- For cleanup-sensitive code, add tests that prove required effects still run.
- For transactional code, test rollback/commit semantics after refactoring.

## Override Notes
- Language/framework docs may narrow early-return style for specific paradigms,
  but should keep the default preference for reduced nesting and explicit flow.
