# PROGRAMMING

Guidance for implementing code changes.

## Expectations
- Confirm the required behavior, acceptance criteria, and constraints before coding.
- Keep changes minimal and scoped to the request; avoid unnecessary refactors.
- Follow architecture rules in `ARCHITECTURE/ARCHITECTURE.md`.
- Apply design principles from `DESIGN/...` and clean-code practices.
- Follow language rules and conventions in `LANGUAGE/...` strictly.
- Use logging rules from `CORE/LOGGING.md` and observability expectations where relevant.
- Treat security and privacy as non-negotiable (`SECURITY/SECURITY.md`).

## Dependencies
- Avoid new dependencies unless clearly justified.
- Evaluate any new dependency against `FRAMEWORK/FRAMEWORK.md`, `LIBRARY/LIBRARY.md`, and
  `COMPLIANCE/COMPLIANCE.md`.

## Testing and Verification
- Follow `TEST/TEST.md`.
- Add missing tests before modifying behavior when current behavior is unclear.
- Add tests for new behavior or edge cases.
- Run relevant tests and report results; if not run, state why.

## Delivery
- Update documentation or comments when behavior changes.
- Summarize changes, tests run, and any residual risks.
