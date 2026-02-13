# SECURITY

Guidance for AI agents implementing and reviewing security-relevant changes.

## Scope
Security is a non-negotiable baseline across all stacks and domains.

## Semantic Dependencies (Upstream Rules)
- Inherits `CORE/CORE.md` and `CORE/RULE_DEPENDENCY_TREE.md` precedence rules.
- Works with `COMPLIANCE/COMPLIANCE.md` and `COMPLIANCE/LICENSES.md` for legal
  and governance constraints.
- Testing expectations are supplemented by `TEST/TEST.md`.

## Security Defaults
- Use secure defaults and explicit allow-lists over broad allow-any behavior.
- Minimize attack surface: disable unused endpoints, ports, capabilities, and
  optional integrations by default.
- Apply least privilege to identities, tokens, credentials, and runtime roles.
- Fail closed on authentication/authorization checks.
- Prefer defense in depth (multiple controls) over single-point controls.

## Secrets and Credentials
- Never commit secrets to source control, docs, examples, logs, or test
  snapshots.
- Use secret managers or secure runtime injection.
- Rotate secrets on exposure, incident, or owner change.
- Keep secret scope minimal (short-lived, environment-specific, least-privilege).
- Redact secrets in logs, telemetry, and error messages.

## Authentication and Authorization
- Treat authentication (identity) and authorization (permissions) as separate
  controls.
- Enforce authorization at trusted boundaries, not only in client/UI paths.
- Validate token audience/issuer/expiry and reject malformed claims.
- Do not rely on obscurity (hidden routes, client checks) as access control.
- Deny by default when user/role/context is ambiguous.

## Input, Output, and Data Protection
- Validate all external input at boundaries (API, file, queue, UI).
- Normalize and canonicalize inputs before policy checks when relevant.
- Use parameterized queries and safe encoders to prevent injection classes.
- Encode/escape output according to sink context (HTML, shell, URL, etc.).
- For SQL sinks, use parameterized queries/bind variables as the primary
  defense rather than string escaping.
- Classify sensitive data and apply minimization, masking, and retention limits.

## Dependency and Supply-Chain Hygiene
- Prefer mature dependencies with active maintenance and clear ownership.
- Pin versions and commit lock files where the ecosystem supports it.
- Track vulnerability advisories and patch high/critical findings quickly.
- Require explicit justification for new runtime dependencies.
- Validate provenance/signatures where tooling supports it.

## Error Handling and Observability
- Do not expose stack traces, internal identifiers, or secret material to
  untrusted callers.
- Return safe, actionable error responses without leaking internals.
- Log security-relevant events (auth failures, policy denials, suspicious
  activity) with safe redaction.
- Ensure security logging itself cannot crash request paths.

## High-Risk Pitfalls
1. Accepting user input without strict validation or context-aware encoding.
2. Storing credentials in code/config/scripts, plaintext CI config, or
   unencrypted/non-secret CI variables instead of the platform secret store.
3. Treating client-side checks as sufficient authorization.
4. Overly broad IAM/role permissions in runtime or CI.
5. Ignoring dependency vulnerabilities due to transitive complexity.
6. Returning detailed internal error content to external callers.
7. Logging secrets or sensitive personal data.
8. Disabling security checks for convenience without tracked exceptions.

## Code Review Checklist
- Are all external inputs validated at trust boundaries?
- Are output sinks context-encoded/escaped appropriately?
- Are secrets absent from source, logs, snapshots, and examples?
- Is authorization enforced server-side with least-privilege semantics?
- Are dependency additions justified, maintained, and compliant?
- Are failure responses safe and non-leaky?
- Are security-sensitive actions auditable through logs/events?
- Are any security exceptions explicit, bounded, and documented?

## Testing Guidance for Security Risks
- Add negative tests for unauthorized/forbidden access paths.
- Add validation tests for malformed, boundary, and malicious inputs.
- Add tests ensuring sensitive fields are redacted in logs/errors.
- Add regression tests for previously found vulnerabilities.
- Validate dependency scanning and policy checks in CI where available.
- If threat model is high risk, include abuse-case tests for key flows.

## Override Notes
- Downstream docs may specialize controls for framework/runtime specifics, but
  must not weaken this baseline without explicit, reviewed rationale.
