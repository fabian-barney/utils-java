# LOGGING

Guidance for logging.

## Purpose
- Support diagnostics, auditing, and incident response.
- Provide enough context to reproduce issues.

## Practices
- Use structured logs (key=value or JSON) where possible.
- Use consistent log levels across services.
- Include correlation or request IDs and the component name.
- Log once per event; avoid logging in tight loops.

## Safety
- Never log secrets or sensitive personal data.
- Redact or hash identifiers when needed.

## Reliability
- Logging must not crash the app; avoid exceptions in logging paths.
- Rate-limit or sample noisy logs.
