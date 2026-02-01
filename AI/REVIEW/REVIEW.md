# REVIEW

## Focus Areas (ordered)
- Correctness and regression risk
- Security and privacy
- Data integrity and error handling
- Observability and logging
- Performance and scalability
- Test coverage and verification gaps
- Cognitive complexity and readability (identify complex methods and propose
  refactors to reduce branching and nesting)

## Dependency Checks
- Newly added public dependencies must align with the selection guidance in
  `AI/FRAMEWORK/FRAMEWORK.md` (and `AI/LIBRARY/LIBRARY.md` where applicable).
- Call out any dependency that appears to be niche, unmaintained, or a poor fit
  for enterprise requirements.

## Output Expectations
- List concrete findings with file references.
- State what was not verified.
- Suggest targeted tests when relevant.
- When cognitive complexity is high, suggest concrete refactors (e.g., extract
  methods, guard clauses, early exits, split responsibilities, introduce strategy).
