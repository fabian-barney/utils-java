# CLEAN_CODE

Concise rules inspired by clean code principles. This is not a summary of the
book; it is a practical checklist for reviews and implementation.

## Naming and Intent
- Use clear, specific names for classes, methods, and variables.
- Avoid ambiguous abbreviations.
- Keep naming consistent within a module.

## Functions and Methods
- Keep functions short and focused on one responsibility.
- Limit parameter count; group related parameters into value objects.
- Prefer early returns to reduce nesting.

## Readability
- Prefer simple, explicit code over cleverness.
- Avoid deeply nested conditionals; extract and name conditions.
- Keep line length reasonable for scanability.

## Comments
- Write comments only when code intent is not obvious.
- Avoid redundant comments that restate the code.

## Error Handling
- Validate inputs early and fail fast with clear exceptions.
- Do not swallow exceptions; add context or rethrow.

## Duplication
- Remove duplication when it adds maintenance risk.
- Do not over-abstract prematurely.

## Structure
- Keep modules cohesive; related code should live together.
- Favor composition over inheritance when possible.
