# SHELL

Guidance for AI agents implementing and reviewing shell scripts.

## Scope
- Define safe and maintainable shell scripting defaults.
- Apply this file to Bash/sh scripting in automation, CI/CD, and local tooling.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit readability conventions from `LANGUAGE/CONVENTIONS.md` and
  `LANGUAGE/READABILITY.md`.
- Build/CI docs may specialize execution contexts but must preserve safety
  constraints here.

## Defaults
- Prefer Bash for non-trivial scripts and declare interpreter explicitly
  (`#!/usr/bin/env bash`).
- Enable strict mode for Bash scripts:
  `set -euo pipefail` and safe `IFS` handling when needed.
- Quote variable expansions by default (`"$var"`).
- Use functions to structure scripts; keep `main` flow explicit.
- Keep scripts idempotent where practical.

## Error Handling and Exit Behavior
- Fail fast on command failures unless explicitly handling error cases.
- Use explicit error messages for failure paths (`echo ... >&2`).
- Check required commands and inputs early.
- For cleanup logic, use `trap` handlers.
- Keep exit codes meaningful and consistent.

## Security Guardrails
- Never build shell commands by concatenating untrusted input.
- Avoid `eval` unless there is no alternative and input is tightly controlled.
- Do not print secrets/tokens to logs.
- Use least-privilege execution; avoid unnecessary `sudo`.
- Validate file paths and arguments before destructive operations.

## Portability and Environment
- Be explicit about shell features requiring Bash vs POSIX sh.
- Avoid relying on environment-specific behavior without checks.
- Use `command -v` for dependency checks.
- Avoid hidden reliance on interactive shell state.

## Data and Loop Handling
- Prefer arrays over word-splitting where Bash arrays are available.
- Use `read -r` to preserve backslashes.
- Avoid parsing command output with brittle text assumptions when machine-
  readable alternatives exist.
- Be careful with globbing and empty-match behavior.

## High-Risk Pitfalls
1. Unquoted expansions causing word splitting and glob injection.
2. Missing strict mode masking failures.
3. Using `eval` with untrusted input.
4. Secret leakage via command echo/logs.
5. Cleanup omission leaving temp files/locks.
6. Destructive commands without path guards.
7. Bash-specific syntax in scripts declared as `/bin/sh`.

## Do / Don't Examples
### 1. Quoting
```bash
# Don't
rm -rf $TARGET_DIR/*

# Do
rm -rf "${TARGET_DIR:?}"/*
```

### 2. Error Handling
```bash
# Don't
cp source.txt "$dest"
echo "done"

# Do
set -euo pipefail
cp source.txt "$dest"
echo "done"
```

### 3. Command Injection Risk
```bash
# Don't
eval "tar -czf backup.tar.gz $INPUT_PATH"

# Do
tar -czf backup.tar.gz -- "$INPUT_PATH"
```

## Code Review Checklist for Shell
- Are the interpreter and strict mode configured correctly?
- Are all variable expansions safely quoted?
- Is untrusted input kept away from command construction/eval?
- Are errors surfaced with meaningful exit behavior?
- Are destructive operations guarded (`${var:?}`, path checks)?
- Are secrets kept out of output/logs?
- Are cleanup handlers present for temp resources?

## Testing Guidance for Shell Scripts
- Run static analysis (`shellcheck`) in CI.
- Test scripts in clean environments (minimal env vars).
- Test failure paths and non-zero exit behavior.
- Test idempotency where scripts may run repeatedly.
- Add integration tests for critical automation scripts.

## Override Notes
- CI/build-tool docs may prescribe runtime wrappers/entrypoints, but strict
  quoting, safety, and injection guardrails here remain mandatory.
