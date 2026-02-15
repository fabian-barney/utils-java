# READABILITY

Guidance for AI agents on writing code with low cognitive load.

## Scope
- Define readability constraints that apply across languages and frameworks.
- Use this file during implementation and review to reduce defect-prone
  complexity.

## Semantic Dependencies
- Inherit design constraints from `DESIGN/CLEAN_CODE.md` and `DESIGN/SOLID.md`.
- `LANGUAGE/CONVENTIONS.md` is a companion baseline that applies alongside this
  file.
- Language/framework docs may specialize patterns but must preserve readability
  and explainability constraints.

## Core Principles
- Optimize for future readers first, then for terse implementation.
- Prefer explicitness over cleverness.
- Keep one level of abstraction per function where practical.
- Make control flow and error flow immediately visible.

## Cognitive Complexity Rules
- Keep functions focused on one responsibility.
- Avoid deep nesting; prefer guard clauses and early returns.
- Split large conditional trees into named predicates or strategy objects.
- Avoid mixing orchestration, transformation, and IO concerns in one function.
- Prefer clear linear flow over intertwined branching.
- Use `DESIGN/EARLY_RETURN.md` for focused early-return defaults and guardrails.

## Expression and Statement Clarity
- Avoid cascading ternary expressions.
- Avoid deeply nested function calls in a single line when intent is unclear.
- Introduce intermediate variables for non-trivial expressions.
- Name intermediate values semantically, not mechanically.
- Keep boolean logic readable; extract complex predicates into named helpers.

## Function and Module Shape
- Keep function length proportionate to complexity.
- Keep parameter lists small and coherent; use value objects for grouped
  concepts.
- Keep related logic close; avoid jumping across distant utility modules for
  core business flow.
- Prefer small cohesive modules over large mixed-responsibility files.

## Comments and Documentation
- Prefer self-explanatory code over explanatory comments.
- Use comments for intent, invariants, and non-obvious tradeoffs.
- Remove or update comments when code changes.
- Avoid narrative comments that duplicate code step-by-step.

## Error Path Readability
- Keep happy path and failure path clearly separated.
- Use explicit error types/messages that communicate cause and recovery context.
- Avoid broad error-handling blocks that hide control flow outcomes.

## High-Risk Pitfalls
1. Deeply nested conditionals obscuring business intent.
2. Functions performing multiple unrelated responsibilities.
3. Dense one-liners hiding side effects and null/error handling.
4. Placeholder naming (`data`, `obj`, `tmp`) masking domain meaning.
5. Comments that drift from behavior and become misleading.
6. Boolean flags controlling unrelated behavior branches.
7. Refactors that shrink lines but increase cognitive load.

## Do / Don't Examples
### 1. Guard Clauses over Nested Blocks
```text
Don't:
if (isValid(request)) {
  if (isAuthorized(user)) {
    if (!isExpired(token)) {
      process(request);
    }
  }
}

Do:
if (!isValid(request)) return;
if (!isAuthorized(user)) return;
if (isExpired(token)) return;
process(request);
```

### 2. Named Intermediate Values
```text
Don't: send(a(b(c(input))));
Do:    const normalized = normalize(input);
       const enriched = enrich(normalized);
       send(enriched);
```

### 3. Avoid Cascading Ternary
```text
Don't: status = a ? "A" : b ? "B" : c ? "C" : "D";
Do:    use explicit if/switch with named intent.
```

## Code Review Checklist for Readability
- Is the main execution flow understandable in one pass?
- Are functions single-purpose and appropriately sized?
- Are nested branches and boolean expressions easy to follow?
- Are names meaningful and domain-specific?
- Are comments useful, current, and non-redundant?
- Are error paths explicit and readable?
- Were abstractions introduced to reduce, not increase, cognitive load?

## Testing Guidance for Readability-Driven Changes
- Add focused regression tests before readability refactors that alter control
  flow.
- Ensure tests cover both happy path and failure path behavior.
- Keep test names behavior-oriented to mirror readability expectations.
- Validate that extraction/refactor steps did not alter side effects.

## Override Notes
- Language/framework docs may define local idioms (for example React hooks,
  Java stream style) but should still satisfy these readability constraints.
