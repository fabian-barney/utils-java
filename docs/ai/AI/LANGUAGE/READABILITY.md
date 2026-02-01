# READABILITY

Rules to promote readable code and low cognitive complexity.

## Principles
- Prefer simple, explicit code over cleverness.
- Keep control flow easy to follow.
- Optimize for the next reader, not the compiler.

## Cognitive Complexity
- Keep functions short and focused on a single responsibility.
- "Only do one thing" per function; split mixed concerns early.
- Avoid deep nesting; extract conditions into named methods.
- Prefer early returns and guard clauses.
- Replace long if/else chains with polymorphism or lookup tables when it helps.
- Limit the number of distinct concepts in a single function.

## Local Variables and Expressions
- Introduce local variables when it improves clarity.
- Avoid deeply nested expressions as function arguments; compute intermediate
  values first so the intent is obvious.
- Name intermediate values to reflect meaning, not mechanics.

## Naming and Structure
- Use clear, descriptive names for variables, methods, and classes.
- Keep related logic close together.
- Avoid large classes; split by responsibility.

## Comments
- Prefer self-explanatory code; most comments should become unnecessary when
  clean code principles are followed.
- Use comments only to clarify intent, constraints, or non-obvious decisions.
- Document structural decisions with ADRs when appropriate.
