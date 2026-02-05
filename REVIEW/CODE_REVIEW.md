# CODE REVIEW

Code review ensures correctness, security, and maintainability before changes land.

## Focus Areas (ordered)
- Architecture and design alignment (flag questionable architectural decisions).
- Correctness and regression risk.
- Security and privacy.
- Data integrity and error handling.
- Observability and logging.
- Performance and scalability.
- Test coverage and verification gaps.
- Cognitive complexity and readability.
- Naming, comments, and documentation quality.
- Language guidance and code conventions (formatting, clean code).

## Dependency Checks
- Newly added public dependencies must align with selection guidance in `FRAMEWORK/FRAMEWORK.md` and
  `LIBRARY/LIBRARY.md`.
- Call out any dependency that appears niche, unmaintained, or a poor fit for enterprise requirements.
- Check license and compliance fit with `COMPLIANCE/COMPLIANCE.md`.
- Require a clear justification for new runtime dependencies.

## Review Actions
- Verify tests exist for behavior changes and edge cases; request missing tests.
- Enforce language-specific rules from `LANGUAGE/...`.
- Enforce design and clean-code rules from `DESIGN/...` and architecture rules from
  `ARCHITECTURE/...`.
- Look for violations of coding conventions, formatting, and readability expectations.
- Ask for smaller, focused changes if the review is too large to evaluate safely.

## Output Expectations
- List concrete findings with severity and file references.
- Reference violated rules by name/path and explain why the rule matters for this case.
- When citing a rule, include a concrete alternative or example tailored to the code.
- Propose small, concrete code changes when possible (patch or pseudocode).
- Call out questionable architecture decisions and questionable new dependencies explicitly.
- State what was not verified and which tests were (or were not) run.
